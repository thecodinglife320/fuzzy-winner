package com.learning.ad.ff.fragment

import android.gesture.*
import android.os.*
import android.view.*
import android.view.ScaleGestureDetector.*
import android.widget.*
import androidx.fragment.app.*
import com.learning.ad.ff.*
import com.learning.ad.ff.databinding.*

class CustomGestureFragment : Fragment() {
   private var scaleFactor =1f
   private val matrix = android.graphics.Matrix()
   private var _binding:FragmentCustomGestureBinding?=null
   private val binding get() = _binding!!
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentCustomGestureBinding.inflate(inflater,container,false)
      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding=null
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      val scaleGestureDetector = ScaleGestureDetector(requireContext(), object : SimpleOnScaleGestureListener() {
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
      val gestureLibrary = GestureLibraries.fromRawResource(requireContext(), R.raw.gestures)
      gestureLibrary.load()
      binding.gOverlay.addOnGesturePerformedListener{ _, gesture ->
         val predictions = gestureLibrary.recognize(gesture)
         if (predictions.isNotEmpty() && predictions[0].score > 1.0) binding.textView4.text = "${binding.textView4.text}${predictions[0].name}"
      }
      binding.gOverlay.addOnGestureListener(object: GestureOverlayView.OnGestureListener{
         var sv = (requireContext() as FragmentActivity).findViewById<ScrollView>(R.id.activity_sv)
         override fun onGestureStarted(overlay: GestureOverlayView?, event: MotionEvent?) {
            sv.requestDisallowInterceptTouchEvent(true)
         }

         override fun onGesture(overlay: GestureOverlayView?, event: MotionEvent?) {
         }

         override fun onGestureEnded(overlay: GestureOverlayView?, event: MotionEvent?) {
            sv.requestDisallowInterceptTouchEvent(false)
         }

         override fun onGestureCancelled(overlay: GestureOverlayView?, event: MotionEvent?) {
            sv.requestDisallowInterceptTouchEvent(false)
         }
      })
      binding.textView4.setOnClickListener {
         (it as TextView).text=""
      }
   }
}