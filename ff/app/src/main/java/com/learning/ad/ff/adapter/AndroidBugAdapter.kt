package com.learning.ad.ff.adapter

import android.view.*
import androidx.recyclerview.widget.RecyclerView.*
import com.google.android.material.snackbar.*
import com.learning.ad.ff.databinding.*
import com.learning.ad.ff.model.*

class AndroidBugAdapter(private val bubDroids: ArrayList<BugDroid>): Adapter<AndroidBugAdapter.AndroidBugViewHolder>() {

   class AndroidBugViewHolder(private val binding:CardLayoutBinding) : ViewHolder(binding.root){
      fun bind(bugDroid: BugDroid) {
         binding.bugDroidImg.setImageResource(bugDroid.image)
         binding.bugDroidNameTv.text = bugDroid.name
         binding.bugDroidDesTv.text = bugDroid.description
         binding.root.setOnClickListener {
            Snackbar.make(it, "${binding.bugDroidNameTv.text}", Snackbar.LENGTH_LONG)
               .setAction("Ok ban",null).show()
         }
      }
   }
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= AndroidBugViewHolder(CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
   override fun getItemCount() = bubDroids.size
   override fun onBindViewHolder(holder: AndroidBugViewHolder, position: Int) = holder.bind(bubDroids[position])
}