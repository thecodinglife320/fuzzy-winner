package com.learning.ad.ff1

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.learning.ad.ff1.databinding.ActivityFlashcardsBinding

class FlashcardsActivity : AppCompatActivity() {
   private lateinit var binding: ActivityFlashcardsBinding
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityFlashcardsBinding.inflate(layoutInflater)
      setContentView(binding.root)
      setSupportActionBar(binding.toolbar)
      val navView: BottomNavigationView = binding.bottomNavigationView
      val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_nav_flash_card) as NavHostFragment
      val navController = navHostFragment.navController
      val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_my_note, R.id.navigation_add_note))
      setupActionBarWithNavController(navController, appBarConfiguration)
      navView.setupWithNavController(navController)
   }
   override fun onCreateOptionsMenu(menu: Menu): Boolean {
      menuInflater.inflate(R.menu.menu_daynight, menu)
      return true
   }
}