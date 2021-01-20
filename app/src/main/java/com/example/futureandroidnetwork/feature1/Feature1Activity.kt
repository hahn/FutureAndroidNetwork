package com.example.futureandroidnetwork.feature1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.futureandroidnetwork.R

class Feature1Activity : AppCompatActivity(), DownloadCallback<String> {

    private var networkFragment: NetworkFragment? = null
    private lateinit var btDownload: Button
    private lateinit var tvResult: TextView
    private var downloading = false

    companion object {
        private var url = "https://reqres.in/api/users/2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature1)
        btDownload = findViewById(R.id.bt_download)
        tvResult = findViewById(R.id.tv_result)

        networkFragment = NetworkFragment.getInstance(supportFragmentManager, url)

        btDownload.setOnClickListener {
            tvResult.text = ""
            startDownload()
        }
    }

    private fun startDownload() {
        if (!downloading) {
            networkFragment?.apply {
                downloading = true
                startDownload()
            }
        }
    }

    override fun updateFromDownload(result: String?) {
        downloading = false
        tvResult.text = "result: $result"
    }


    override fun onProgressUpdate(progressCode: Int, percentComplete: Int) {

    }

    override fun finishDownloading() {
        Toast.makeText(this, "Download finished", Toast.LENGTH_LONG).show()
    }
}