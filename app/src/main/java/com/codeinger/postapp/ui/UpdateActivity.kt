package com.codeinger.postapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.codeinger.postapp.databinding.ActivityUpdateBinding
import com.codeinger.postapp.model.PostModel
import com.codeinger.postapp.utils.UpdateState
import com.codeinger.postapp.viewmodel.UpdateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateActivity : AppCompatActivity() {

    private val updateViewModel: UpdateViewModel by viewModels()

    private lateinit var postModel: PostModel
    private var id: Int = 0
    private lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id", 0)

        response()

        binding.done.setOnClickListener {
            var body: String = binding.body.text.toString()
            var title: String = binding.title.text.toString()

            if (validate(body, title)) {
                postModel = PostModel(1, id, title, body)
                updateViewModel.getPostUpdate("" + id, postModel)
            } else {
                Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_LONG)
                    .show()

            }
        }


    }

    private fun response() {
        lifecycleScope.launchWhenStarted {

            updateViewModel.postsLiveData.observe(this@UpdateActivity, {
                when (it) {

                    is UpdateState.Loading -> {
                    }

                    is UpdateState.Failure -> {
                        Log.d("cggds", "onCreate: ${it.msg}")

                    }
                    is UpdateState.Success -> {

                        Log.d("hvjvhv", "onCreate: " + it.data.toString())

                    }

                    is UpdateState.Empty -> {

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