package com.codeinger.postapp.network

import com.codeinger.postapp.model.CreateModel
import com.codeinger.postapp.model.PostModel
 import retrofit2.http.*

interface ApiService {
    @GET("posts")
    suspend fun getPosts() : List<PostModel>

    @GET("posts/{id}")
    suspend fun getParticularPost(@Path("id") id: String) : PostModel


    @POST("posts")
    suspend fun getCreatePost(@Body createReq : CreateModel ) : CreateModel

    @PUT("posts/{id}")
    suspend fun getUpdatePost(@Path("id") id: String,@Body postModel: PostModel) : PostModel
}


