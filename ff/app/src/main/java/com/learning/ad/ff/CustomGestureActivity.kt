package com.learning.ad.ff

import android.gesture.*
import android.opengl.Matrix
import android.os.*
import android.util.*
import android.view.*
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.widget.*
import androidx.appcompat.app.*
import com.learning.ad.ff.databinding.*

class CustomGestureActivity : AppCompatActivity() {
   private var scaleFactor =1f
   private val matrix = android.graphics.Matrix()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      val binding = ActivityCustomGestureBinding.inflate(layoutInflater)
      setContentView(binding.root)
      val gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures)
      if (gestureLibrary.load()) Log.d("CustomGestureActivity", "Success")
      binding.gOverlay.addOnGesturePerformedListener{ _, gesture ->
         val predictions = gestureLibrary.recognize(gesture)
         if (predictions.size > 0 && predictions[0].score > 1.0)
            binding.textView4.text = binding.textView4.text.toString()+predictions[0].name
      }
      binding.textView4.setOnClickListener {
         (it as TextView).text=""
      }
      val scaleGestureDetector = ScaleGestureDetector(this, object : SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
               scaleFactor *= detector.scaleFactor
               scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f))
               matrix.setScale(scaleFactor, scaleFactor,binding.imageView2.width/2f,binding.imageView2.height/2f)
               binding.imageView2.imageMatrix = matrix
               return true
            }

            override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
               // Called when a scale gesture begins
               return true
            }

            override fun onScaleEnd(detector: ScaleGestureDetector) {
               // Called when a scale gesture ends
            }
         })
      binding.imageView2.setOnTouchListener {_,m: MotionEvent ->
         scaleGestureDetector.onTouchEvent(m)
         true
      }
   }
}