package com.dicoding.kotlin.submission2githubuser.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.kotlin.submission2githubuser.GithubUser
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class UserDetailViewModel : ViewModel() {
    val githubUserDetail = MutableLiveData<ArrayList<GithubUser>>()

    fun returnUserDetail(githubUser: GithubUser){
        val apiToken = "54173ba775f452d264a13dcba8c7842250f28443"

        val detailClient = AsyncHttpClient()
        detailClient.addHeader("Authorization", "token $apiToken")
        detailClient.addHeader("User-Agent","request")
        detailClient.get(githubUser.url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val detailResult = responseBody?.let { JSONObject(String(it)) }
                githubUser.name = detailResult?.getString("name")
                githubUser.followers = detailResult?.getInt("followers")

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getUserDetail(): LiveData<ArrayList<GithubUser>> {
        return githubUserDetail
    }
}