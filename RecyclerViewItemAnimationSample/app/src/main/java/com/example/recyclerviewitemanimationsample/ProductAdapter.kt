package com.example.recyclerviewitemanimationsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewitemanimationsample.databinding.ItemProductBinding

class ProductAdapter(
    private val onProductLiked: (Int) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder = ProductViewHolder.from(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(getItem(position), position, onProductLiked)

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentProduct: Product, position: Int, onProductLiked: (Int) -> Unit) {
            binding.productName.text = currentProduct.productName
            binding.productImage.setImageResource(currentProduct.productImage)
            binding.likeAnim.progress = if (currentProduct.isLiked) 1f else 0f
            binding.likeAnim.setOnClickListener {
                onProductLiked(position)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
                return ProductViewHolder(binding)
            }
        }
    }

    private class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productName == newItem.productName
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        /*If DiffUtil recognizes that there is a change with the previous two methods, 
        it will trigger this method to get corresponding payload*/
        override fun getChangePayload(oldItem: Product, newItem: Product): Any? {
            return when {
                !oldItem.isLiked && newItem.isLiked -> LIKE_ITEM
                oldItem.isLiked && !newItem.isLiked -> DISLIKE_ITEM
                else -> null
            }
        }
    }
}
