package com.learning.ad.ff.fragment

import android.os.*
import android.util.*
import android.view.*
import android.widget.*
import androidx.constraintlayout.widget.*
import androidx.constraintlayout.widget.ConstraintSet.*
import androidx.fragment.app.*
import com.learning.ad.ff.R

class KotlinLayoutFragment : Fragment() {
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      super.onCreate(savedInstanceState)
      return configureLayout()
   }

   private fun configureLayout():View {
      val myButton = Button(context)
      val myLayout = ConstraintLayout(requireContext())
      val set = ConstraintSet()
      val myEdt = EditText(context)
      myEdt.id = R.id.myEditText
      myEdt.width= 100.convertToPx()
      myButton.id = R.id.myButton

      myLayout.addView(myButton)
      myLayout.addView(myEdt)

      set.constrainWidth(myButton.id, WRAP_CONTENT)
      set.constrainHeight(myButton.id, WRAP_CONTENT)
      set.connect(myButton.id, START, PARENT_ID, START)
      set.connect(myButton.id, END, PARENT_ID, END)
      set.connect(myButton.id, TOP, PARENT_ID, TOP)
      set.connect(myButton.id, BOTTOM, PARENT_ID, BOTTOM)

      set.constrainWidth(myEdt.id, WRAP_CONTENT)
      set.constrainHeight(myEdt.id, WRAP_CONTENT)
      set.connect(myEdt.id, START, PARENT_ID, START)
      set.connect(myEdt.id, END, PARENT_ID, END)
      set.connect(myEdt.id, BOTTOM, myButton.id, TOP)
      set.applyTo(myLayout)
      return myLayout
   }
   private fun Int.convertToPx(): Int {
      val r = resources
      return TypedValue.applyDimension(
         TypedValue.COMPLEX_UNIT_DIP,
         toFloat(),
         r.displayMetrics
      ).toInt()
   }
}