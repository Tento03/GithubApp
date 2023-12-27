package com.example.tes.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tes.api.GithubApi
import com.example.tes.api.User
import com.example.tes.api.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private var githubApi: GithubApi) {
   fun searchUser(query:String):LiveData<UserResponse>{
       var search=MutableLiveData<UserResponse>()
       githubApi.searchUser(query).enqueue(object :Callback<UserResponse>{
           override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
               if (response.isSuccessful){
                   search.postValue(response.body())
               }
           }

           override fun onFailure(call: Call<UserResponse>, t: Throwable) {
           }

       })
       return search
   }

    fun detailUser(username:String):LiveData<User>{
        var detail=MutableLiveData<User>()
        githubApi.getUserDetail(username).enqueue(object :Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    detail.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return detail
    }
}