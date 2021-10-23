package com.example.recyclerviewitemanimationsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewitemanimationsample.databinding.ItemProductBinding

class ProductAdapter(
    viewModel: ProductViewModel,
    private val productLikeListener: ProductLikeListener
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    private val productList = viewModel.productList.also {
        submitList(it)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder = ProductViewHolder.from(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(productList[position], position, productLikeListener)

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentProduct: Product, position: Int, listener: ProductLikeListener) {
            binding.productName.text = currentProduct.productName
            binding.productImage.setImageResource(currentProduct.productImage)
            binding.likeAnim.progress = if (currentProduct.isLiked) 1f else 0f
            binding.likeAnim.setOnClickListener {
                listener.onLikeClicked(position)
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

    interface ProductLikeListener {
        fun onLikeClicked(position: Int)
    }

    private class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}
