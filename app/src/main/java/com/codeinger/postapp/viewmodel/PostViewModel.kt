package com.codeinger.postapp.viewmodel

 import androidx.lifecycle.LiveData
 import androidx.lifecycle.MutableLiveData
 import androidx.lifecycle.ViewModel
 import androidx.lifecycle.viewModelScope
 import com.codeinger.postapp.repository.PostRepository
import com.codeinger.postapp.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
 import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
 import kotlinx.coroutines.flow.collect
 import kotlinx.coroutines.launch
 import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postRepository: PostRepository ) : ViewModel(){

    private val mutablePosts : MutableLiveData<ApiState> = MutableLiveData()
    val postsLiveData : LiveData<ApiState> = mutablePosts


     fun getPost() =viewModelScope.launch {
         mutablePosts.value =ApiState.Loading
        postRepository.getPosts()
            .catch {e->
                mutablePosts.value=ApiState.Failure(e)

            }.collect { data->
                mutablePosts.value=ApiState.Success(data)
            }
    }
}