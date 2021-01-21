package com.example.futureandroidnetwork.feature3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.futureandroidnetwork.R

class Feature3Activity : AppCompatActivity() {

    private lateinit var queue: RequestQueue

    companion object {
        private const val url = "https://reqres.in/api/users/1"
        private const val TAG = "MyTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature3)
        queue = Volley.newRequestQueue(this)
        findViewById<Button>(R.id.bt_download).setOnClickListener {
            downloadUrl()
        }
    }

    private fun downloadUrl() {
        val stringRequest = StringRequest(Request.Method.GET, url, {
            showText(it)
        }, {
            showText("error: ${it.message}")
        })
        stringRequest.tag = TAG
        queue.add(stringRequest)
    }

    private fun showText(text: String) {
        findViewById<TextView>(R.id.tv_result).text = "result: $text"
    }

    override fun onStop() {
        super.onStop()
        //cancel all requests
        queue.cancelAll(TAG)
    }


}