package com.codeinger.postapp.utils

import com.codeinger.postapp.model.PostModel

sealed class ParticularPostApiState {
    object Loading : ParticularPostApiState()
    class Failure(val msg : Throwable) : ParticularPostApiState()
    class Success(val data: PostModel) : ParticularPostApiState()
    object Empty: ParticularPostApiState()
}