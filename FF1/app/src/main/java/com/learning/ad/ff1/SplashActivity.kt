package com.learning.ad.ff1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Executors

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
   private val executor = Executors.newSingleThreadExecutor()
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_splash)
      executor.submit {
         Thread.sleep(2000)
         runOnUiThread {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
         }
      }
   }
}