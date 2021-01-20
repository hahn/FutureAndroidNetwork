package com.example.futureandroidnetwork.feature2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.futureandroidnetwork.R
import okhttp3.*
import java.io.IOException

class Feature2Activity : AppCompatActivity() {

    private var downloading = false
    private val url = "https://reqres.in/api/users/2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature2)

        findViewById<Button>(R.id.bt_download).setOnClickListener {
            if (!downloading) {
                downloading = true
                printResult("loading...")
                startDownloadUrl()
            }
        }
    }

    private fun startDownloadUrl() {
        val client = OkHttpClient()
        var result = ""
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                downloading = false
                result = "error connection: ${e.message}"
                printResult(result)
            }

            override fun onResponse(call: Call, response: Response) {
                downloading = false
                response.run {
                    result = if (!isSuccessful) {
                        "error code: $code"
                    } else {
                        body?.string() ?: "empty"
                    }
                    printResult(result)
                }
            }
        })
    }

    private fun printResult(resultString: String) {
        runOnUiThread {
            findViewById<TextView>(R.id.tv_result).text = "result: $resultString"
        }
    }
}