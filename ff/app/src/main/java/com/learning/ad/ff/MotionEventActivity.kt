package com.learning.ad.ff

import android.os.*
import android.view.*
import android.view.MotionEvent.*
import androidx.appcompat.app.*
import com.learning.ad.ff.databinding.*

class MotionEventActivity : AppCompatActivity() {
   private lateinit var binding:ActivityMotionEventBinding
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMotionEventBinding.inflate(layoutInflater)
      setContentView(binding.root)
      binding.root.setOnTouchListener {_,m: MotionEvent ->
         handleTouch(m)
         true
      }
   }

   private fun handleTouch(m: MotionEvent) {
      for (i in 0 until m.pointerCount){
         val x = m.getX(i)
         val y = m.getY(i)
         val id = m.getPointerId(i)
         val action = m.actionMasked
         val actionIndex = m.actionIndex
         val actionStr:String = when(action){
            ACTION_DOWN-> "DOWN"
            ACTION_UP->"UP"
            ACTION_POINTER_DOWN->"POINTER DOWN"
            ACTION_POINTER_UP->"POINTER UP"
            ACTION_MOVE->"MOVE"
            else -> ""
         }
         val touchStatus= "Action: $actionStr \n"+
           "Index action:$actionIndex \n" +
           "ID pointer: $id \n" +
           "X: $x \n" +
           "Y: $y \n"
         if ((id==0)) binding.textView.text=touchStatus
         else binding.textView2.text=touchStatus
      }
   }
}