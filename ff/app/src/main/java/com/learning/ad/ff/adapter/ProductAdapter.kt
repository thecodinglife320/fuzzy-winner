package com.learning.ad.ff.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learning.ad.ff.R
import com.learning.ad.ff.databinding.ProductItemBinding
import com.learning.ad.ff.model.Product

class ProductAdapter(private val listener: ProductClickListener):ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {
    interface ProductClickListener {
        fun onProductClick(product: Product)
    }
    class ProductViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product) {
            binding.productNameTv.text =binding.root.context.getString(R.string.product_name1, product.name)
            binding.productQuantityTv.text =binding.root.context.getString(R.string.product_quantity1, product.quantity)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int){
        holder.bind(getItem(position))
        val product = getItem(position)
        holder.itemView.setOnClickListener {
            listener.onProductClick(product)
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id // Use unique ID for comparison
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem // Compare all fields for content changes
        }
    }
}