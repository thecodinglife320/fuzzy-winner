package com.learning.ad.ff1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.learning.ad.ff1.adapter.StateAdapter
import com.learning.ad.ff1.databinding.ActivityStateTriviaBinding

class StateTriviaActivity : AppCompatActivity() {
   private lateinit var binding: ActivityStateTriviaBinding
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityStateTriviaBinding.inflate(layoutInflater)
      setContentView(binding.root)
      setSupportActionBar(binding.materialToolbar)
      val stateList = getStateData()
      val stateAdapter = StateAdapter(stateList) {
         val query = "https://www.google.com/search?q=${it.name}"
         val intent = Intent(Intent.ACTION_VIEW, Uri.parse(query))
         startActivity(intent)
      }
      binding.stateRv.adapter = stateAdapter
      binding.stateRv.layoutManager = LinearLayoutManager(this)
   }
   override fun onCreateOptionsMenu(menu: Menu?): Boolean {
      menu?.add(0, 0, 0, "List View")
      menu?.add(0, 1, 0, "Grid View")
      menu?.add(0, 2, 0, "Staggered View")
      return super.onCreateOptionsMenu(menu)
   }
   override fun onOptionsItemSelected(item: MenuItem): Boolean {
      when (item.itemId) {
         0 -> binding.stateRv.layoutManager = LinearLayoutManager(this)
         1 -> binding.stateRv.layoutManager = GridLayoutManager(this,2)
         2 -> binding.stateRv.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
      }
      return super.onOptionsItemSelected(item)
   }
}