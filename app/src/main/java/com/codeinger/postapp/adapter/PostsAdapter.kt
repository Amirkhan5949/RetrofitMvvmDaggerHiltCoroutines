package com.codeinger.postapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.codeinger.postapp.databinding.PostlistBinding
import com.codeinger.postapp.model.PostModel
import com.codeinger.postapp.ui.ParicularPostActivity

class PostsAdapter(private var postlist : List<PostModel>) : RecyclerView.Adapter<PostsAdapter.PostsAdapter_View>() {

    private lateinit var binding: PostlistBinding
    class PostsAdapter_View(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter_View {
        binding= PostlistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostsAdapter_View(binding.root)
    }

    override fun onBindViewHolder(holder: PostsAdapter_View, position: Int) {
        binding.title.text= postlist[position].title
        binding.body.text= postlist[position].body

        binding.particularPost.setOnClickListener {

             val intent =Intent(binding.particularPost.context, ParicularPostActivity::class.java).apply {
                 putExtra("id", postlist[position].id)

             }
            binding.particularPost.context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return postlist.size
    }

    fun setData(post: List<PostModel>)
    {
        this.postlist=post
        notifyDataSetChanged()
    }
}