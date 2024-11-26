package com.learning.ad.ff.adapter

import android.view.*
import androidx.recyclerview.widget.RecyclerView.*
import com.learning.ad.ff.databinding.*

class RVadapter(private val textItems: ArrayList<String>): Adapter<RVadapter.TextItemViewHolder>() {

   class TextItemViewHolder(private val binding: RecyclerViewItemBinding) : ViewHolder(binding.root){
      fun bind(textItem: String) {
         binding.textView1.text = textItem
      }
   }
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= TextItemViewHolder(
      RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
   override fun getItemCount() = textItems.size
   override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) = holder.bind(textItems[position])
}