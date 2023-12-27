package com.example.tes.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @Headers("Authorization: Bearer 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")
    @GET("search/users")
    fun searchUser(
        @Query("q")q:String
    ):Call<UserResponse>

    @Headers("Authorization: Bearer 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")
    @GET("users/{username}")
    fun getUserDetail(
        @Path("username")username:String
    ):Call<User>

    @Headers("Authorization: Bearer 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")
    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username")username:String
    ):Call<ArrayList<User>>

    @Headers("Authorization: Bearer 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")
    @GET("users/{username}/following")
    fun getFollowings(
        @Path("username")username:String
    ):Call<ArrayList<User>>
}