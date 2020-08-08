package com.dicoding.kotlin.submission2githubuser.utils

import com.dicoding.kotlin.submission2githubuser.data.SearchUserResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIInterface {
    @GET("search/users?q={user}&type=Users&sort=followers")
    fun returnUserSearch(@Path("user") users: String) : Call<List<SearchUserResults>>
}