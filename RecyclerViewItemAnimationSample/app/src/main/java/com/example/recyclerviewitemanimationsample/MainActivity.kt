package com.example.recyclerviewitemanimationsample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewitemanimationsample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

const val HEART_ANIM = 6728

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ProductAdapter.ProductLikeListener {

    private var productAdapter: ProductAdapter? = null

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productAdapter = ProductAdapter(viewModel, this)

        val dividerItemDecoration = DividerItemDecoration(
            this, LinearLayoutManager.VERTICAL
        )

        binding.productsList.apply {
            addItemDecoration(dividerItemDecoration)
            itemAnimator = ProductItemAnimator()
            adapter = productAdapter
        }
    }

    override fun onLikeClicked(position: Int) {
        // Shuffle like-dislike
        viewModel.productList[position].isLiked = !(viewModel.productList[position].isLiked)
        // You would probably persist this info in a real life case.

        // Notify adapter of what has changed
        productAdapter?.notifyItemChanged(position, HEART_ANIM)
    }
}
