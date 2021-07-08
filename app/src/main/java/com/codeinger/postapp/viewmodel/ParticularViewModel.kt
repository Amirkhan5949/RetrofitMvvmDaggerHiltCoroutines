package com.codeinger.postapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeinger.postapp.repository.ParticularPostRepository
import com.codeinger.postapp.utils.ParticularPostApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
 class ParticularViewModel @Inject constructor(private var particularPostRepository: ParticularPostRepository): ViewModel(){

    private val mutablePosts : MutableLiveData<ParticularPostApiState> = MutableLiveData()
    val postsLiveData : LiveData<ParticularPostApiState> = mutablePosts


    fun getParticularPost(id: String) =viewModelScope.launch {
        mutablePosts.value = ParticularPostApiState.Loading
        particularPostRepository.getParticularPost(id)
            .catch {e->
                mutablePosts.value= ParticularPostApiState.Failure(e)

            }.collect { data->
                mutablePosts.value= ParticularPostApiState.Success(data)
            }
    }
}