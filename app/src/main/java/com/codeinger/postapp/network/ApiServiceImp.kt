package com.codeinger.postapp.network

import com.codeinger.postapp.model.CreateModel
import com.codeinger.postapp.model.PostModel
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.PUT
import javax.inject.Inject


class ApiServiceImp @Inject constructor(private val apiService: ApiService){

    suspend fun getPosts(): List<PostModel> =apiService.getPosts()

    suspend fun getParticularPost(id: String): PostModel =apiService.getParticularPost(id)

    suspend fun createPost(createModel: CreateModel): CreateModel = apiService.getCreatePost(createModel)

    suspend fun getUpdatePost(id: String,postModel: PostModel):  PostModel =apiService.getUpdatePost(id,postModel)


}
