package com.codeinger.postapp.utils

import com.codeinger.postapp.model.PostModel

sealed class UpdateState{

    object Loading : UpdateState()
    class Failure(val msg : Throwable) : UpdateState()
    class Success(val data: PostModel) : UpdateState()
    object Empty: UpdateState()
}
