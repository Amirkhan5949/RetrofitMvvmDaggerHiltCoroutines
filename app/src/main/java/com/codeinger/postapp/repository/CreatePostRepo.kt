package com.codeinger.postapp.repository

import com.codeinger.postapp.model.CreateModel
import com.codeinger.postapp.network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CreatePostRepo @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    fun createPost(createModel: CreateModel): Flow<CreateModel> =
         flow {
             emit(apiServiceImp.createPost(createModel))
         }.flowOn(Dispatchers.IO)
}