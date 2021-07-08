package com.codeinger.postapp.utils

import com.codeinger.postapp.model.CreateModel


sealed class CreatePostState{
    object Loading : CreatePostState()
    class Failure(val msg : Throwable) : CreatePostState()
    class Success(val data: CreateModel) : CreatePostState()
    object Empty: CreatePostState()

}
