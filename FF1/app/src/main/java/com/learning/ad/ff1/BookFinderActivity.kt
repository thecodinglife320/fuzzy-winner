package com.learning.ad.ff1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.learning.ad.ff1.databinding.ActivityBookFinderBinding

class BookFinderActivity : AppCompatActivity() {
   private lateinit var binding: ActivityBookFinderBinding
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityBookFinderBinding.inflate(layoutInflater)
      setContentView(binding.root)
      setSupportActionBar(binding.toolbar)
      val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_nav_book_finder) as NavHostFragment
      val navController = navHostFragment.navController
      val appBarConfiguration = AppBarConfiguration(navController.graph)
      NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration)
      NavigationUI.setupWithNavController(binding.toolbar,navController)
   }
}