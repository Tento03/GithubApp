package com.example.tes.follow

import androidx.lifecycle.*
import com.example.tes.api.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowViewModel @Inject constructor(private var repository: FollowRepository,
private val savedStateHandle: SavedStateHandle):ViewModel() {
    fun getFollowers(username:String):LiveData<ArrayList<User>>{
        return repository.getfollowers(username)
    }
    fun getFollowing(username: String):LiveData<ArrayList<User>>{
        return repository.getfollowing(username)
    }
}
