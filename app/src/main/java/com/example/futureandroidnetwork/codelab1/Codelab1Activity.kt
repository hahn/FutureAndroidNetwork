package com.example.futureandroidnetwork.codelab1

import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.futureandroidnetwork.R
import okhttp3.*
import java.io.IOException
import java.io.InputStream

class Codelab1Activity : AppCompatActivity() {

    companion object {
        private val url = "https://reqres.in/img/faces/1-image.jpg"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_codelab1)

        findViewById<Button>(R.id.bt_okhttp).setOnClickListener {
            showImageWithOkHttp()
        }
    }

    private fun showImageWithOkHttp() {
        val request = Request.Builder()
            .url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                showErrorMessage("error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val contentType = response.header("content-type")
                    if (contentType == "image/jpeg") {
                        showToast("image nih $contentType")
                        val result = response.body?.byteStream()
                        result?.let { showImage(it) }
                    } else {
                        showToast("bukan image: $contentType")
                        showErrorMessage("Bukan image")

                    }
                } else {
                    //get non 200
                    showErrorMessage("error: ${response.code}")
                }
            }
        })
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun showImage(stream: InputStream) {
        val bmp = BitmapFactory.decodeStream(stream)
        runOnUiThread {
            findViewById<ImageView>(R.id.imageView).setImageBitmap(bmp)
        }
    }

    private fun showErrorMessage(message: String) {
        runOnUiThread {
            findViewById<TextView>(R.id.tv_error).text = message
        }
    }
}