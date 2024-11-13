package com.learning.ad.ff.adapter

import androidx.fragment.app.*
import androidx.viewpager2.adapter.*
import com.learning.ad.ff.fragment.*

class TabPagerAdapter(fa:FragmentActivity,private var tabCount:Int): FragmentStateAdapter(fa) {
   override fun getItemCount(): Int {
      return tabCount
   }

   override fun createFragment(position: Int): Fragment {
      return when (position){
         0-> Tab1Fragment()
         1-> Tab2Fragment()
         2-> Tab3Fragment()
         3-> Tab4Fragment()
         else -> Tab1Fragment()
      }
   }
}