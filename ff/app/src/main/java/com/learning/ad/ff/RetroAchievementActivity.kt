package com.learning.ad.ff

import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.core.os.*
import androidx.lifecycle.*
import androidx.navigation.*
import androidx.navigation.fragment.*
import androidx.navigation.ui.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.fragment.*
import com.learning.ad.ff.viewmodel.*

class RetroAchievementActivity : AppCompatActivity(),UserFragment.UserFragmentListener{
   private lateinit var userName:String
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      val binding = ActivityRetroAchievementBinding.inflate(layoutInflater)
      userName = RetroAchievementActivityArgs.fromBundle(intent.extras!!).userName
      setContentView(binding.root)
      setSupportActionBar(binding.toolbar)

      val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
      //cau hinh AppBar
      val builder = AppBarConfiguration
         .Builder(navHostFragment.navController.graph)
         .setOpenableLayout(binding.drawerLayout)
      binding.toolbar.setupWithNavController(navHostFragment.navController,builder.build())
      binding.navDrawer.setupWithNavController(navHostFragment.navController)


   }
   override fun onCreateOptionsMenu(menu: Menu): Boolean {
      menuInflater.inflate(R.menu.menu_retro_achievment_drawer, menu)
      return super.onCreateOptionsMenu(menu)
   }
   override fun onOptionsItemSelected(item: MenuItem): Boolean {
      val navController = findNavController(R.id.nav_host_fragment)
      return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
   }
   override fun fetchUserProfile() {
      val navController = findNavController(R.id.nav_host_fragment)
      val backStackEntry = navController.getBackStackEntry(R.id.userFragment)
      val viewModel = ViewModelProvider(backStackEntry)[UserViewModel::class.java]
      viewModel.fetchProfileUser(userName)
   }
}