package com.learning.ad.ff

import android.content.*
import android.net.*
import android.os.*
import android.util.Log
import androidx.appcompat.app.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.observer.*

class SecondActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      val binding = ActivitySecondBinding.inflate(layoutInflater)
      val extras = intent.extras ?: return
      binding.showWebPageBtn.setOnClickListener {
         Log.d(TAG,"A")
         startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com")))
      }
      setContentView(binding.root)
   }
   override fun finish() {
      val data = Intent()
      data.putExtra("return", "returnString")
      setResult(RESULT_OK, data)
      super.finish()
   }
}