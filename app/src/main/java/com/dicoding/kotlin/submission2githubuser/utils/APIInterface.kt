package com.dicoding.kotlin.submission2githubuser.utils

import com.dicoding.kotlin.submission2githubuser.GithubUser
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("items")
    fun returnUserSearch() : Call<List<GithubUser>>
}