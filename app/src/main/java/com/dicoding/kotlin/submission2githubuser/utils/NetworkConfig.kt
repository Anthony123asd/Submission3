package com.dicoding.kotlin.submission2githubuser.utils

import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NetworkConfig {
    // set interceptor
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }

    fun getRetrofit(user: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/search/users?q=$user&type=Users&sort=followers")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(user : String) = getRetrofit(user).create(Users::class.java)
}

interface Users {
    @GET("users")
    fun getUsers(): Call<List<GithubUsers>>
}