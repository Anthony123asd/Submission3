package com.dicoding.kotlin.submission2githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.SyncHttpClient
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MainViewModel2 : ViewModel(){
    val listGithubUsers = MutableLiveData<ArrayList<GithubUser>>()

    fun returnUserSearch(user: String) {
        val listUsers = ArrayList<GithubUser>()
        val apiToken = "54173ba775f452d264a13dcba8c7842250f28443"
        val searchURL = "https://api.github.com/search/users?q=$user&type=Users"

        val searchClient = AsyncHttpClient()
        searchClient.addHeader("Authorization", "token $apiToken")
        searchClient.addHeader("User-Agent","request")
        searchClient.get(searchURL, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val searchResult = responseBody?.let { JSONObject(String(it)) }
                    val items =searchResult?.getJSONArray("items")
                    //val userClient = SyncHttpClient()
                    for (i in 0 until items!!.length()) {
                        val item = items.getJSONObject(i)
                        val githubUser = GithubUser()
                        val userClient = SyncHttpClient()
                        userClient.addHeader("Authorization", "token $apiToken")
                        userClient.addHeader("User-Agent","request")
                        githubUser.avatarURL = item.getString("avatar_url")
                        githubUser.id = item.getInt("id")
                        githubUser.url = item.getString("url")
                        githubUser.login = item.getString("login")
                        listUsers.add(githubUser)
                    }

                    listGithubUsers.postValue(listUsers)

                } catch (error: Exception) {
                    Log.d("Exception", error.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("Failed: ", statusCode.toString());
                Log.d("Error : ", error.toString());
            }

        })
    }

    fun getUsers(): LiveData<ArrayList<GithubUser>> {
        return listGithubUsers
    }
}


