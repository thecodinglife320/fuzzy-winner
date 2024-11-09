package com.learning.ad.ff

import android.graphics.*
import android.os.*
import android.util.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.*
import androidx.constraintlayout.widget.ConstraintSet.*

class KotlinLayoutActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      configureLayout()
   }

   private fun configureLayout() {
      val myButton = Button(this)
      val myLayout = ConstraintLayout(this)
      val set = ConstraintSet()
      val myEdt = EditText(this)
      myEdt.id = R.id.myEditText
      myEdt.width=convertToPx(32)
//      myButton.text = getString(R.string.press_me)
      myButton.setBackgroundColor(Color.YELLOW)
      myButton.id = R.id.myButton
      //
      //myLayout.setBackgroundColor(Color.BLUE)
      myLayout.addView(myButton)
      myLayout.addView(myEdt)
      setContentView(myLayout)
      //
      set.constrainWidth(myButton.id, WRAP_CONTENT)
      set.constrainHeight(myButton.id, WRAP_CONTENT)
      set.connect(myButton.id, START, PARENT_ID, START)
      set.connect(myButton.id, END, PARENT_ID, END)
      set.connect(myButton.id, TOP, PARENT_ID, TOP)
      set.connect(myButton.id, BOTTOM, PARENT_ID, BOTTOM)
      //
      set.constrainWidth(myEdt.id, WRAP_CONTENT)
      set.constrainHeight(myEdt.id, WRAP_CONTENT)
      set.connect(myEdt.id, START, PARENT_ID, START)
      set.connect(myEdt.id, END, PARENT_ID, END)
      set.connect(myEdt.id, BOTTOM, myButton.id, TOP,convertToPx(8))
      set.applyTo(myLayout)
   }
   private fun convertToPx(value: Int): Int {
      val r = resources
      return TypedValue.applyDimension(
         TypedValue.COMPLEX_UNIT_DIP,
         value.toFloat(),
         r.displayMetrics
      ).toInt()
   }
}