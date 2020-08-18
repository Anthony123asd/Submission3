package com.dicoding.kotlin.submission2githubuser.utils

class GithubUserRepo {
    suspend fun getService(): APIInterface = NetworkConfig().getRetrofit().create(APIInterface::class.java)
}