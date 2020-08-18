package com.dicoding.kotlin.submission2githubuser.utils


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConfig {
    // set interceptor
    private fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Authorization","54173ba775f452d264a13dcba8c7842250f28443")
                        .addHeader("User-Agent","request")
                        .build()
                    return chain.proceed(newRequest)
                }
            })
            .addInterceptor(logging)
            .build()
    }

    internal fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}