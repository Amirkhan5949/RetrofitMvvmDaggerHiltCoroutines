package com.codeinger.postapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.codeinger.postapp.databinding.ActivityCreateBinding
import com.codeinger.postapp.model.CreateModel
import com.codeinger.postapp.utils.CreatePostState
import com.codeinger.postapp.viewmodel.CreatePostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class CreateActivity : AppCompatActivity() {

    private val createPostViewModel: CreatePostViewModel by viewModels()

    private lateinit var createModel: CreateModel

    private lateinit var binding: ActivityCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.done.setOnClickListener {
            var body: String = binding.body.text.toString()
            var title: String = binding.title.text.toString()

            if (validate(body, title)) {
                createModel= CreateModel(1,title,body)
                createPostViewModel.createPost(createModel)
            } else {
                Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_LONG)
                    .show()

            }
        }


        response()

    }


    private fun response() {
        lifecycleScope.launchWhenStarted {

            createPostViewModel.postsLiveData.observe(this@CreateActivity, {
                when (it) {

                    is CreatePostState.Loading -> {
                    }

                    is CreatePostState.Failure -> {
                        Log.d("cggds", "onCreate: ${it.msg}")

                    }
                    is CreatePostState.Success -> {

                        Log.d("hvjvhv", "onCreate: " + it.data.toString())

                    }

                    is CreatePostState.Empty -> {

                    }
                }
            })


        }

    }

    fun validate(
        title: String,
        body: String
    ): Boolean {
        return (title.isNotEmpty() && body.isNotEmpty())
    }
}