package com.learning.ad.ff

import android.net.*
import android.os.*
import androidx.fragment.app.*
import com.learning.ad.ff.fragment.*
import com.learning.ad.ff.fragment.FirstFragment.*
import com.learning.ad.ff.lifecycleowner.*

class MainActivity : FragmentActivity(), FirstFragmentListener,MotionEventFragment.OnFragmentInteractionListener {
   private lateinit var lifecycleOwner: MainActivityLOwner

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)
      lifecycleOwner = MainActivityLOwner()
   }

   override fun onButtonClick(fontSize: Int, text: String) { (supportFragmentManager.findFragmentById(R.id.second_fragment) as SecondFragment).changeTextProperties(fontSize, text) }
   override fun onFragmentInteraction(uri: Uri) {

   }
}