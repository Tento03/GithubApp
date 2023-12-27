package com.example.tes.github

import androidx.lifecycle.*
import com.example.tes.api.User
import com.example.tes.api.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(private var repository: GithubRepository,
private val savedStateHandle: SavedStateHandle):ViewModel() {
    companion object{
        const val DEFAULT_QUERY="luffy"
        const val CURRENT_QUERY="current"
    }
    private var currentQuery=savedStateHandle.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    fun detailUser(username:String):LiveData<User>{
        return repository.detailUser(username)
    }

    var github=currentQuery.switchMap {
        repository.searchUser(it)
    }
    fun searchUser(username:String){
        currentQuery.value=username
        repository.searchUser(username)
    }
}