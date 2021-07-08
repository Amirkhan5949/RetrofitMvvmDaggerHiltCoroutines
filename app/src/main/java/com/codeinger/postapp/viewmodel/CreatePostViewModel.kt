package com.codeinger.postapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeinger.postapp.model.CreateModel
import com.codeinger.postapp.repository.CreatePostRepo
import com.codeinger.postapp.utils.CreatePostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(private val createPostRepo: CreatePostRepo) :
    ViewModel() {

    private val mutablePosts : MutableLiveData<CreatePostState> = MutableLiveData()
    val postsLiveData : LiveData<CreatePostState> = mutablePosts

    fun createPost(createModel: CreateModel) = viewModelScope.launch {
        mutablePosts.value = CreatePostState.Loading
        createPostRepo.createPost(createModel)
            .catch {e->
                mutablePosts.value= CreatePostState.Failure(e)

            }.collect { data->
                mutablePosts.value= CreatePostState.Success(data)
            }
    }

}