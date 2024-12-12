package com.learning.ad.ff1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.learning.ad.ff1.R
import com.learning.ad.ff1.databinding.BookCardViewBinding
import com.learning.ad.ff1.network.Book

class BookAdapter(val searchOnWeb: (Book) -> Unit):ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallBack()){
   class BookDiffCallBack:DiffUtil.ItemCallback<Book>(){
      override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem.volumeInfo.infoLink == newItem.volumeInfo.infoLink
      override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem ==newItem
   }
   class BookViewHolder(val binding:BookCardViewBinding):ViewHolder(binding.root){
      fun bind(book: Book){
         binding.book = book
         binding.executePendingBindings()
      }
   }
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder = BookViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.book_card_view,parent,false))
   override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
      val book = getItem(position)
      holder.bind(book)
      holder.itemView.setOnClickListener { searchOnWeb(book) }
   }
}