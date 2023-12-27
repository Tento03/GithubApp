package com.example.tes.follow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tes.api.GithubApi
import com.example.tes.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowRepository @Inject constructor(private var githubApi: GithubApi) {
    fun getfollowers(username: String):LiveData<ArrayList<User>>{
        var followers=MutableLiveData<ArrayList<User>>()
        githubApi.getFollowers(username).enqueue(object :Callback<ArrayList<User>>{
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful){
                    followers.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return followers
    }
    fun getfollowing(username: String):LiveData<ArrayList<User>>{
        var followings=MutableLiveData<ArrayList<User>>()
        githubApi.getFollowings(username).enqueue(object :Callback<ArrayList<User>>{
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful){
                    followings.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return followings
    }
}