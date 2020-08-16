package com.dicoding.kotlin.submission2githubuser

import android.util.Log
import androidx.lifecycle.*
import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import com.dicoding.kotlin.submission2githubuser.data.SearchUserResults
import com.dicoding.kotlin.submission2githubuser.data.UserDetail
import com.dicoding.kotlin.submission2githubuser.utils.NetworkConfig
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel2 : ViewModel(){
    protected val listGithubUsers = MutableLiveData<ArrayList<GithubUsers?>>()
    private val networkConfig = NetworkConfig()


    fun returnUserSearch(user: String) {
        viewModelScope.launch (Dispatchers.IO){
            val arrayGithubUsers = ArrayList<GithubUsers?>()
            val users = networkConfig.getService().returnUserSearch(user = user, sort = "followers")?.body()?.items

            if (users != null) {
                for (user in users){
                    val userDetail = networkConfig.getService().returnUserDetail(user?.login.toString())?.body()
                    user?.name = userDetail?.name
                    user?.publicRepos = userDetail?.publicRepos
                    user?.followers = userDetail?.followers
                    arrayGithubUsers?.add(user)
                }
            }

            listGithubUsers.postValue(arrayGithubUsers)

        }
    }


    fun getUsers(): LiveData<ArrayList<GithubUsers?>> {
        return listGithubUsers
    }
}


