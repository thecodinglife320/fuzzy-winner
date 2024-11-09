package com.learning.ad.ff

import android.os.*
import androidx.fragment.app.*
import com.learning.ad.ff.fragment.*

class MainActivity : FragmentActivity(),FirstFragment.FirstFragmentListener {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)
   }

   override fun onButtonClick(fontSize: Int, text: String) {
      (supportFragmentManager.findFragmentById(R.id.second_fragment) as SecondFragment).changeTextProperties(fontSize,text)
   }
}