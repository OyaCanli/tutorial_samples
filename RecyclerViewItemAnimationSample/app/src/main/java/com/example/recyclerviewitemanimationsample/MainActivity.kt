package com.example.recyclerviewitemanimationsample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewitemanimationsample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productAdapter = ProductAdapter(onProductLiked = { position ->
            viewModel.toggleLikeAtPosition(position)
        })

        val dividerItemDecoration = DividerItemDecoration(
            this, LinearLayoutManager.VERTICAL
        )

        binding.productsList.apply {
            addItemDecoration(dividerItemDecoration)
            itemAnimator = ProductItemAnimator()
            adapter = productAdapter
        }

        viewModel.productList.observe(this, {
            productAdapter.submitList(it)
        })
    }
}
