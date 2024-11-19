package com.learning.ad.ff

import android.content.*
import android.net.*
import android.os.*
import android.provider.*
import androidx.appcompat.app.*
import com.learning.ad.ff.databinding.*

class SecondActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      val binding = ActivitySecondBinding.inflate(layoutInflater)
      //val extras = intent.extras ?: return
      binding.showWebPageBtn.setOnClickListener {
         startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://vietcodedi.com")))
      }

      binding.enableLinkBtn.setOnClickListener {
         val intent = Intent(Settings.ACTION_APP_OPEN_BY_DEFAULT_SETTINGS)
         intent.data = Uri.parse("package:$packageName")
         startActivity(intent)
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