package com.learning.ad.ff

import android.content.*
import android.net.*
import android.os.*
import android.provider.*
import android.util.Log
import androidx.appcompat.app.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.observer.*

class SecondActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      val binding = ActivitySecondBinding.inflate(layoutInflater)
      //val extras = intent.extras ?: return
      binding.showWebPageBtn.setOnClickListener {
         startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com")))
      }

      binding.enableLinkBtn.setOnClickListener {
         startActivity(Intent(
            Settings.ACTION_APP_OPEN_BY_DEFAULT_SETTINGS,
            Uri.parse("package:com.learning.ad.ff")))
      }
      setContentView(binding.root)
      Log.d(TAG,packageName)
   }
   override fun finish() {
      val data = Intent()
      data.putExtra("return", "returnString")
      setResult(RESULT_OK, data)
      super.finish()
   }
}