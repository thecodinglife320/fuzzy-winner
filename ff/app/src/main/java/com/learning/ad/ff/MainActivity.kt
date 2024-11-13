package com.learning.ad.ff

import android.os.*
import androidx.fragment.app.*
import androidx.navigation.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.fragment.*
import com.learning.ad.ff.fragment.FirstFragment.*
import com.learning.ad.ff.lifecycleowner.*

class MainActivity : FragmentActivity(), FirstFragmentListener,MainFragment.MainFragmentListener {
   private lateinit var lifecycleOwner: MainActivityLOwner
   private lateinit var binding: ActivityMainBinding
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)
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

   override fun goToKotlinLayoutFragment() {
      val action=MainFragmentDirections.actionMainFragmentToKotlinLayoutFragment()
      findNavController(R.id.activity_main_nav_host_fragment).navigate(action)
   }

   override fun goToCommonGestureFragment() {
      val action=MainFragmentDirections.actionMainFragmentToCommonGesturesFragment()
      findNavController(R.id.activity_main_nav_host_fragment).navigate(action)
   }

   override fun goToCustomGestureFragment() {
      findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToCustomGestureFragment())
   }

   override fun goToTabLayoutFragment() {
      findNavController(R.id.activity_main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToTabLayoutFragment())
   }
}