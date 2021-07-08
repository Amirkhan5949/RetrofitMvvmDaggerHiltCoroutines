package com.codeinger.postapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeinger.postapp.adapter.PostsAdapter
import com.codeinger.postapp.databinding.ActivityMainBinding
import com.codeinger.postapp.utils.ApiState
import com.codeinger.postapp.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postsAdapter: PostsAdapter

    private val postViewModel: PostViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
        postViewModel.getPost()


        lifecycleScope.launchWhenStarted {

            postViewModel.postsLiveData.observe(this@MainActivity, {
                when (it) {

                    is ApiState.Loading -> {
                        binding.rvPost.isVisible = false
                        binding.progressBar.isVisible = true
                    }

                    is ApiState.Failure -> {
                        binding.rvPost.isVisible = false
                        binding.progressBar.isVisible = false

                    }
                    is ApiState.Success -> {
                        binding.rvPost.isVisible = true
                        binding.progressBar.isVisible = false
                        postsAdapter.setData(it.data)
                        postsAdapter.notifyDataSetChanged()
                    }

                    is ApiState.Empty -> {

                    }
                }
            })


        }


    }

    private fun initRecyclerview() {

        postsAdapter = PostsAdapter(ArrayList())
        binding.rvPost.apply {

            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postsAdapter
        }

    }
}