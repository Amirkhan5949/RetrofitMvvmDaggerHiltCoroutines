package com.codeinger.postapp.utils

import com.codeinger.postapp.model.PostModel


sealed class ApiState {
 object Loading : ApiState()
class Failure(val msg : Throwable) : ApiState()
    class Success (val data : List<PostModel>) : ApiState()
object Empty: ApiState()

}

