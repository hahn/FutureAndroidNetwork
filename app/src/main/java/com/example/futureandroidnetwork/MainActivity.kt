package com.example.futureandroidnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.futureandroidnetwork.feature2.Feature2Activity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    findViewById<Button>(R.id.bt_feature2).setOnClickListener {
      startActivity(Intent(this, Feature2Activity::class.java))
    }
  }
}