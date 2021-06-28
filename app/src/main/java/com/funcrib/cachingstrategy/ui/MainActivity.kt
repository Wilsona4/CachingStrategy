package com.funcrib.cachingstrategy.ui

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.funcrib.cachingstrategy.databinding.ActivityMainBinding
import com.funcrib.cachingstrategy.ui.adapter.RestaurantRvAdapter
import com.funcrib.cachingstrategy.util.Resource
import com.funcrib.cachingstrategy.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerViewAdapter: RestaurantRvAdapter


    private val viewModel: MainViewModel by viewModels()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Initialize View*/
        recyclerView = binding.rvRestaurant
        progressBar = binding.progressBar

        /*Setup Recycler View*/
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RestaurantRvAdapter()
            adapter = recyclerViewAdapter
        }

        viewModel.getRestaurant.observe(this) {
            it.data?.let { it1 -> recyclerViewAdapter.submitList(it1) }
            progressBar.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
            binding.tvError.isVisible = it is Resource.Error && it.data.isNullOrEmpty()
            binding.tvError.text = it.errorBody?.message()
        }
    }
}