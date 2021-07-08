package com.codeinger.postapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeinger.postapp.model.PostModel
import com.codeinger.postapp.repository.UpdateRepo
import com.codeinger.postapp.utils.UpdateState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(private val updateRepo: UpdateRepo) : ViewModel() {

    private val mutablePosts : MutableLiveData<UpdateState> = MutableLiveData()
    val postsLiveData : LiveData<UpdateState> = mutablePosts

    fun getPostUpdate(id: String,postModel: PostModel) =viewModelScope.launch {
        mutablePosts.value = UpdateState.Loading
        updateRepo.getPostUpdate(id,postModel)
            .catch {e->
                mutablePosts.value= UpdateState.Failure(e)

            }.collect { data->
                mutablePosts.value= UpdateState.Success(data)
            }
    }


}

