package com.example.futureandroidnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.futureandroidnetwork.codelab1.Codelab1Activity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    findViewById<Button>(R.id.bt_codelab).setOnClickListener {
      startActivity(Intent(this, Codelab1Activity::class.java))
    }
  }
}