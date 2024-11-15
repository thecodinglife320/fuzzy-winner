package com.learning.ad.ff

import android.os.*
import androidx.appcompat.app.*
import com.learning.ad.ff.databinding.*
import java.net.*

class WebViewActivity : AppCompatActivity() {
   private lateinit var binding: ActivityWebViewBinding
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityWebViewBinding.inflate(layoutInflater)
      setContentView(binding.root)
      handleIntent()
   }
   private fun handleIntent() {
      val intent = this.intent
      val data = intent.data
      var url: URL? = null
      try {
         url = URL(data?.scheme, data?.host, data?.path)}
      catch (e: Exception) {
         e.printStackTrace()
      }
      binding.webView.loadUrl(url.toString())
   }
}