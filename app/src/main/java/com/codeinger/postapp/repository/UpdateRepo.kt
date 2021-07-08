package com.codeinger.postapp.repository

import com.codeinger.postapp.model.PostModel
import com.codeinger.postapp.network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateRepo @Inject constructor(private val apiServiceImp: ApiServiceImp){

    fun getPostUpdate(id: String,postModel: PostModel): Flow<PostModel> =
        flow {
            emit(apiServiceImp.getUpdatePost(id,postModel))
        }.flowOn(Dispatchers.IO)


}