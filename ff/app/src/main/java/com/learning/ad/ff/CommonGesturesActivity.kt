package com.learning.ad.ff

import android.os.*
import android.view.*
import android.view.GestureDetector.*
import androidx.appcompat.app.*
import androidx.core.view.*
import com.learning.ad.ff.databinding.*

class CommonGesturesActivity : AppCompatActivity(), OnGestureListener, OnDoubleTapListener{
   private lateinit var binding:ActivityCommonGesturesBinding
   private var gDetector: GestureDetectorCompat? = null
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      this.gDetector = GestureDetectorCompat(this, this)
      gDetector?.setOnDoubleTapListener(this)
      binding = ActivityCommonGesturesBinding.inflate(layoutInflater)
      setContentView(binding.root)
      }

   override fun onTouchEvent(event: MotionEvent): Boolean {
      this.gDetector?.onTouchEvent(event)
      return super.onTouchEvent(event)
   }

   override fun onDown(e: MotionEvent): Boolean {
      binding.gestureStatusTv.text="onDown"
      return true
   }
   override fun onShowPress(e: MotionEvent) {
      binding.gestureStatusTv.text = "onShowPress"
   }
   override fun onSingleTapUp(e: MotionEvent): Boolean {
      binding.gestureStatusTv.text = "onSingleTapUp"
      return true
   }

   override fun onScroll(
      e1: MotionEvent?,
      e2: MotionEvent,
      distanceX: Float,
      distanceY: Float
   ): Boolean {
      binding.gestureStatusTv.text = "onScroll"
      return true
   }

   override fun onLongPress(e: MotionEvent) {
      binding.gestureStatusTv.text = "onLongPress"
   }

   override fun onFling(
      e1: MotionEvent?,
      e2: MotionEvent,
      velocityX: Float,
      velocityY: Float
   ): Boolean {
      binding.gestureStatusTv.text = "onFling"
      return true
   }

   override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
      binding.gestureStatusTv.text = "onSingleTapConfirmed"
      return true
   }

   override fun onDoubleTap(e: MotionEvent): Boolean {
      binding.gestureStatusTv.text = "onDoubleTap"
      return true
   }

   override fun onDoubleTapEvent(e: MotionEvent): Boolean {
      binding.gestureStatusTv.text = "onDoubleTapEvent"
      return true
   }
}