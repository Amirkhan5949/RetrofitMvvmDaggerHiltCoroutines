package com.codeinger.postapp.repository

import com.codeinger.postapp.model.PostModel
import com.codeinger.postapp.network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.Flow
import javax.inject.Inject

class PostRepository @Inject constructor( private val apiServiceImp: ApiServiceImp) {

    fun getPosts() : kotlinx.coroutines.flow.Flow<List<PostModel>> =
        flow {
            emit(apiServiceImp.getPosts())
        }.flowOn(Dispatchers.IO)

}