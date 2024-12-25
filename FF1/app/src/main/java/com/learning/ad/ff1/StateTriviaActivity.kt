package com.learning.ad.ff1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.learning.ad.ff1.databinding.ActivityStateTriviaBinding
import com.learning.ad.ff1.databinding.StateItemLayoutBinding

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
class StateAdapter(private val states: List<State>, private val onClick: (State) -> Unit) : RecyclerView.Adapter<StateAdapter.ViewHolder>() {
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
      StateItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val state = states[position]
      holder.bind(state)
      holder.itemView.setOnClickListener {
         onClick(state)
      }
   }
   override fun getItemCount() = states.size
   class ViewHolder(private val binding: StateItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
      fun bind(state: State) {
         binding.stateTv.text = state.name
         binding.titleTv.text = state.title
      }
   }
}
data class State(val name: String, val title: String)
fun getStateData(): List<State> {
   val states = mutableListOf<State>()
   states.add(State(name = "Rajasthan", title = "Land of Kings"))
   states.add(State(name = "Kerala", title = "Gods' Own Country"))
   states.add(State(name = "Punjab", title = "Land of Five Rivers"))
   states.add(State(name = "Bihar", title = "Land of Buddha"))
   states.add(State(name = "Goa", title = "Party capital of India"))
   states.add(State(name = "Uttar Pradesh", title = "Land of the Taj Mahal"))
   states.add(State(name = "Tamil Nadu", title = "Land of Temples"))
   states.add(State(name = "Maharashtra", title = "Gateway of India"))
   states.add(State(name = "Gujarat", title = "Land of the Mahatma"))
   states.add(State(name = "Madhya Pradesh", title = "Heart of India"))
   return states
}