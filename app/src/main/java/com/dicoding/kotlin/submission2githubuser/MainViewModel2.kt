package com.dicoding.kotlin.submission2githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import com.dicoding.kotlin.submission2githubuser.data.SearchUserResults
import com.dicoding.kotlin.submission2githubuser.utils.NetworkConfig
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel2 : ViewModel(){
    val listGithubUsers = MutableLiveData<ArrayList<GithubUser>>()
    private lateinit var networkConfig: NetworkConfig


    fun returnUserSearch(user: String) {

        networkConfig.run {
            /*getService().returnUserSearch(user).enqueue(object : Callback<List<GithubUsers>>,
                retrofit2.Callback<List<GithubUsers>> {
                override fun onFailure(call: Call<List<GithubUsers>>, t: Throwable) {
                    Log.d("OnFailure",t.toString())
                }

                override fun onResponse(
                    call: Call<List<GithubUsers>>,
                    response: Response<List<GithubUsers>>
                ) {
                    val result = response.body()
                    Log.d("OnSuccess", result.toString())

                }

            })*/

            getService().returnUserSearch(user).enqueue(object : Callback<List<SearchUserResults>>{})
        }
    }

    fun getUsers(): LiveData<ArrayList<GithubUser>> {
        return listGithubUsers
    }
}


