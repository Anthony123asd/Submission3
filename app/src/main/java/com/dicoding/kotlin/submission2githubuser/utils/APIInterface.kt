package com.dicoding.kotlin.submission2githubuser.utils

import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import com.dicoding.kotlin.submission2githubuser.data.SearchUserResults
import com.dicoding.kotlin.submission2githubuser.data.UserDetail
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {
    @GET("search/users")
//    suspend fun returnUserSearch(@Query("q") user: String, @Query("sort") sort: String) : Call<SearchUserResults>?
    suspend fun returnUserSearch(@Query("q") user: String, @Query("sort") sort: String) : Response<SearchUserResults>?

    @GET("users/{user}/followers")
    suspend fun returnFollowersList(@Path("user") user: String) : List<GithubUsers>?

    @GET("users/{user}")
//    suspend fun returnUserDetail(@Path("user") user: String) : Call<GithubUsers>?
    suspend fun returnUserDetail(@Path("user") user: String) : Response<UserDetail>?
}