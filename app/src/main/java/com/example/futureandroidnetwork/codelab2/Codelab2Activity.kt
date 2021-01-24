package com.example.futureandroidnetwork.codelab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.futureandroidnetwork.R
import com.example.futureandroidnetwork.codelab2.model.GithubUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Codelab2Activity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_codelab2)
        rvUsers = findViewById(R.id.rv_users)
        progressBar = findViewById(R.id.progressbar)
        getListGithubUser()
    }

    fun getListGithubUser() {
        showProgressBar(true)
        val call = GithubApiClient.getClient.getUsersList()
        call.enqueue(object : Callback<List<GithubUser>> {
            override fun onResponse(
                call: Call<List<GithubUser>>,
                response: Response<List<GithubUser>>
            ) {
                showProgressBar(false)
                if (response.isSuccessful) {
                    val adapter = GithubAdapter(response.body().orEmpty()) {
                        val user = it.login
                        val intent = Intent(this@Codelab2Activity, GithubRepoListActivity::class.java)
                        intent.putExtra("user", user)
                        startActivity(intent)
                    }
                    rvUsers.adapter = adapter
                    rvUsers.layoutManager = LinearLayoutManager(this@Codelab2Activity)
                    adapter.notifyDataSetChanged()
                } else {
                    showToast("connect to Gihutb failed")
                }
            }

            override fun onFailure(call: Call<List<GithubUser>>, t: Throwable) {
                showProgressBar(false)
                showToast("Error connection. error: ${t.message}")
                t.printStackTrace()
            }
        })
    }

    private fun showProgressBar(isShown: Boolean) {
        if (isShown) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}