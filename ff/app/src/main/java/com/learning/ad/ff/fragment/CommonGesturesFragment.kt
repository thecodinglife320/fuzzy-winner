package com.learning.ad.ff.fragment

import android.os.*
import android.view.*
import android.view.GestureDetector.*
import androidx.core.view.*
import androidx.fragment.app.*
import com.learning.ad.ff.databinding.*

class CommonGesturesFragment : Fragment(), OnGestureListener, OnDoubleTapListener{
   private var _binding:FragmentCommonGesturesBinding?=null
   private val binding get() = _binding!!
   private var gDetector: GestureDetectorCompat? = null
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      super.onCreate(savedInstanceState)
      _binding = FragmentCommonGesturesBinding.inflate(inflater,container,false)
      return binding.root
      }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      this.gDetector = GestureDetectorCompat(requireContext(), this)
      gDetector?.setOnDoubleTapListener(this)
      binding.root.setOnTouchListener { _, event ->
         gDetector?.onTouchEvent(event)
         true
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
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