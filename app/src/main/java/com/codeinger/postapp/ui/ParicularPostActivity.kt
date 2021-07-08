package com.codeinger.postapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.codeinger.postapp.databinding.ActivityParicularPostBinding
import com.codeinger.postapp.utils.ParticularPostApiState
import com.codeinger.postapp.viewmodel.ParticularViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParicularPostActivity : AppCompatActivity() {

    private val particularViewModel: ParticularViewModel by viewModels()

    private lateinit var binding: ActivityParicularPostBinding
    private   var id : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityParicularPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id =intent.getIntExtra("id",0)


        particularViewModel.getParticularPost(""+id)


        binding.floating.setOnClickListener {
            val intent =Intent(binding.floating.context, UpdateActivity::class.java).apply {
                putExtra("id",id)

            }
            binding.floating.context.startActivity(intent)
        }

        lifecycleScope.launchWhenStarted {

            particularViewModel.postsLiveData.observe(this@ParicularPostActivity, {
                when (it) {

                    is ParticularPostApiState.Loading -> {
                         binding.progressBar.isVisible = true
                    }

                    is ParticularPostApiState.Failure -> {
                         binding.progressBar.isVisible = false
                        Log.d("cggds", "onCreate: ${it.msg}")

                    }
                    is ParticularPostApiState.Success -> {
                         binding.progressBar.isVisible = false
                        binding.title.setText(it.data.title)
                        binding.body.setText(it.data.body)
                        Log.d("hvjvhv", "onCreate: "+it.data.toString())

                    }

                    is ParticularPostApiState.Empty -> {

                    }
                }
            })


        }


    }
}