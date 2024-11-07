package com.learning.ad.ff

import android.os.Bundle
import android.util.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.learning.ad.ff.databinding.ActivityLifeCycleBinding
import com.learning.ad.ff.databinding.DemoRatiosBinding

class LifeCycleActivity : AppCompatActivity() {
   private val TAG = "StateChange"
   private lateinit var binding:DemoRatiosBinding
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = DemoRatiosBinding.inflate(layoutInflater)
      setContentView(binding.root)
      Log.i(TAG,"onCreate")
   }

   override fun onStart() {
      super.onStart()
      Log.i(TAG,"onStart")
   }

   override fun onResume() {
      super.onResume()
      Log.i(TAG,"onResume")
   }

   override fun onPause() {
      super.onPause()
      Log.i(TAG,"onPause")
   }

   override fun onStop() {
      super.onStop()
      Log.i(TAG,"onStop")
   }

   override fun onRestart() {
      super.onRestart()
      Log.i(TAG,"onRestart")
   }

   override fun onDestroy() {
      super.onDestroy()
      Log.i(TAG,"onDestroy")
   }
}