package com.codeinger.postapp.repository

import com.codeinger.postapp.model.PostModel
import com.codeinger.postapp.network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ParticularPostRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    fun getParticularPost(id: String): Flow<PostModel> =
        flow {
            emit(apiServiceImp.getParticularPost(id))
        }.flowOn(Dispatchers.IO)
}