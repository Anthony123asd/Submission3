package com.dicoding.kotlin.submission2githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.kotlin.submission2githubuser.data.SearchUserResults
import com.dicoding.kotlin.submission2githubuser.utils.NetworkConfig
import com.google.gson.Gson
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Response


class MainViewModel2 : ViewModel(){
    private val listGithubUsers = MutableLiveData<ArrayList<GithubUser>>()

    fun returnUserSearch(user: String) {
        val searchResults : List<SearchUserResults>
        NetworkConfig().getService().returnUserSearch(user, "followers").enqueue(object : retrofit2.Callback<SearchUserResults>{
                override fun onFailure(call: Call<SearchUserResults>, t: Throwable) {
                    Log.d("OnFailure",t.toString())
                }

                override fun onResponse(
                    call: Call<SearchUserResults>,
                    response: Response<SearchUserResults>
                ) {
                    var SearchUserResults = SearchUserResults()
                    val result = response.body()
                    Log.d("OnSuccess", result.toString())

                    SearchUserResults.items = result?.items



                }
        })
    }


    fun getUsers(): LiveData<ArrayList<GithubUser>> {
        return listGithubUsers
    }
}


