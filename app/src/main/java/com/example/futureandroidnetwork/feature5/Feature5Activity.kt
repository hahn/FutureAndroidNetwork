package com.example.futureandroidnetwork.feature5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.futureandroidnetwork.R
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Feature5Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature5)

        findViewById<Button>(R.id.bt_download).setOnClickListener {
            downloadUserDetail()
        }
    }

    private fun downloadUserDetail() {
        val call: Call<ResponseBody> = ApiClient.getClient.getUserDetail(1)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val result = response.body()?.string()
                    result?.let {
                        showText(it)
                    }
                } else {
                    showText("not success")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                t.message?.let { showText(it) }
            }
        })
    }

    private fun showText(resultString: String) {
        findViewById<TextView>(R.id.tv_result).text = "result: $resultString"


    }


}