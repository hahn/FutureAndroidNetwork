package com.example.futureandroidnetwork.codelab1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.futureandroidnetwork.R
import okhttp3.*
import java.io.*
import java.net.URL

class Codelab1Activity : AppCompatActivity(), DownloadCallback<Bitmap> {

    private var callback: DownloadCallback<Bitmap>? = null
    companion object {
        private const val url = "https://reqres.in/img/faces/1-image.jpg"
        private const val url2 = "https://reqres.in/img/faces/2-image.jpg"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_codelab1)

        findViewById<Button>(R.id.bt_okhttp).setOnClickListener {
            showImageWithOkHttp()
        }

        findViewById<Button>(R.id.bt_asynctask).setOnClickListener {
            showImageWithASynctask()
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

    private fun showImageWithASynctask() {
        callback = this as? DownloadCallback<Bitmap>
        callback?.let {
            DownloadTask(it).apply {
                execute(url2)
            }
        }
    }

    override fun onSuccess(result: Bitmap?) {
        findViewById<ImageView>(R.id.imageView).setImageBitmap(result)
    }

    override fun onFailure(ex: Exception) {
        ex.printStackTrace()
        ex.message?.let { showErrorMessage(it) }
    }

    override fun onDestroy() {
        callback = null
        super.onDestroy()
    }

    private class DownloadTask(private val callback: DownloadCallback<Bitmap>): AsyncTask<String, Int, DownloadTask.ResponseResult>() {
        override fun doInBackground(vararg urls: String?): ResponseResult? {
            var result: ResponseResult? = null

            if (!isCancelled && urls.isNotEmpty()) {
                val urlString = urls[0]
                result = try {
                    val inputStream = URL(urlString).openStream()
                    ResponseResult(BitmapFactory.decodeStream(inputStream))
                } catch (e: Exception) {
                    ResponseResult(e)
                }
            }

            return result
        }

        override fun onPostExecute(result: ResponseResult?) {
            result?.resultValue?.also {
                callback.onSuccess(it)
            }
            result?.exception?.also {
                callback.onFailure(it)
            }
        }

        class ResponseResult {
            var resultValue: Bitmap? = null
            var exception: Exception? = null

            constructor(resultValue: Bitmap) {
                this.resultValue = resultValue
            }

            constructor(exception: Exception) {
                this.exception = exception
            }
        }
    }


}