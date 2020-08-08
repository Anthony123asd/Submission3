package com.dicoding.kotlin.submission2githubuser.utils

import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import com.dicoding.kotlin.submission2githubuser.data.SearchUserResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {
    @GET("search/users")
    fun returnUserSearch(@Query("q") user: String, @Query("sort") sort: String) : Call<SearchUserResults>

    @GET("users/{user}/followers")
    fun returnFollowersList(@Path("user") user: String) : Call<List<GithubUsers>>
}