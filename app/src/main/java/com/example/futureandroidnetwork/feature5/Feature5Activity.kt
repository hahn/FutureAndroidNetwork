package com.example.futureandroidnetwork.feature5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.futureandroidnetwork.R
import com.example.futureandroidnetwork.codelab2.Codelab2Activity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Feature5Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature5)

        findViewById<Button>(R.id.bt_download).setOnClickListener {
            startActivity(Intent(this, Codelab2Activity::class.java))
        }
    }

    private fun downloadUserDetail() {
        val call: Call<ApiResponse> = ApiClient.getClient.getUserDetail(1)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                response.body()?.data?.email?.let { showText("email: $it") }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                t.printStackTrace()
                showText("error: ${t.message}")
            }
        })
    }

    private fun showText(resultString: String) {
        findViewById<TextView>(R.id.tv_result).text = "result: $resultString"
    }

}