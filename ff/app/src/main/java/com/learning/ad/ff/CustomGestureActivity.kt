package com.learning.ad.ff

import android.gesture.*
import android.os.*
import android.util.*
import androidx.appcompat.app.*
import com.learning.ad.ff.databinding.*

class CustomGestureActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      val binding = ActivityCustomGestureBinding.inflate(layoutInflater)
      setContentView(binding.root)
      val gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures)
      if (gestureLibrary.load()) Log.d("CustomGestureActivity", "Success")
      binding.gOverlay.addOnGesturePerformedListener{ _, gesture ->
         val predictions = gestureLibrary.recognize(gesture)
         if (predictions.size > 0 && predictions[0].score > 1.0) {
            Log.d("CustomGestureActivity", predictions[0].name)
         }
      }
   }
}