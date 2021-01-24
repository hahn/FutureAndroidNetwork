package com.example.futureandroidnetwork.codelab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.futureandroidnetwork.R
import com.example.futureandroidnetwork.codelab2.model.GithubRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepoListActivity : AppCompatActivity() {

    var user = ""
    private lateinit var rvRepos: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_repo_list)
        user = intent.getStringExtra("user").orEmpty()
        rvRepos = findViewById(R.id.rv_repos)
        progressBar = findViewById(R.id.progressbar)
        getRepoList()

    }

    private fun getRepoList() {
        showProgressBar(true)
        val call = GithubApiClient.getClient.getReposList(user)
        call.enqueue(object : Callback<List<GithubRepo>> {
            override fun onResponse(
                call: Call<List<GithubRepo>>,
                response: Response<List<GithubRepo>>
            ) {
                showProgressBar(false)
                if (response.isSuccessful) {
                    val adapter = GithubRepoAdapter(response.body().orEmpty())
                    rvRepos.adapter = adapter
                    rvRepos.layoutManager = LinearLayoutManager(this@GithubRepoListActivity)
                    adapter.notifyDataSetChanged()
                } else {
                    showToast("failed to connect to Github")
                }
            }

            override fun onFailure(call: Call<List<GithubRepo>>, t: Throwable) {
                showProgressBar(false)
                showToast("error: ${t.message}")
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

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}