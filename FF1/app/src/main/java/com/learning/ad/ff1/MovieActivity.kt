package com.learning.ad.ff1

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.learning.ad.ff1.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

   private lateinit var appBarConfiguration: AppBarConfiguration
   private lateinit var binding: ActivityMovieBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      binding = ActivityMovieBinding.inflate(layoutInflater)
      setContentView(binding.root)

      setSupportActionBar(binding.toolbar)

      val navController = findNavController(R.id.nav_host_fragment_content_movie)
      appBarConfiguration = AppBarConfiguration(navController.graph)
      setupActionBarWithNavController(navController, appBarConfiguration)

      binding.fab.setOnClickListener { view ->
         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .setAnchorView(R.id.fab).show()
      }
   }

   override fun onSupportNavigateUp(): Boolean {
      val navController = findNavController(R.id.nav_host_fragment_content_movie)
      return navController.navigateUp(appBarConfiguration)
          || super.onSupportNavigateUp()
   }
}