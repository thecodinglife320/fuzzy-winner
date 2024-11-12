package com.learning.ad.ff

import android.os.*
import androidx.fragment.app.*
import androidx.navigation.*
import com.learning.ad.ff.fragment.*
import com.learning.ad.ff.fragment.FirstFragment.*
import com.learning.ad.ff.lifecycleowner.*

class MainActivity : FragmentActivity(), FirstFragmentListener,MainFragment.MainFragmentListener {
   private lateinit var lifecycleOwner: MainActivityLOwner
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)
      lifecycleOwner = MainActivityLOwner()
   }

   override fun onButtonClick(fontSize: Int, text: String) { (supportFragmentManager.findFragmentById(R.id.second_fragment) as SecondFragment).changeTextProperties(fontSize, text) }

   override fun goToMotionEventFragment() {
      val action = MainFragmentDirections
         .actionMainFragmentToMotionEventFragment()
      findNavController(R.id.activity_main_nav_host_fragment).navigate(action)
   }

   override fun goToMotionLayoutFragment() {
      val action=MainFragmentDirections.actionMainFragmentToMotionLayoutFragment()
      findNavController(R.id.activity_main_nav_host_fragment).navigate(action)
   }
}