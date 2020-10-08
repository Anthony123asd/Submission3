package com.dicoding.kotlin.submission2githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import com.dicoding.kotlin.submission2githubuser.utils.GithubUserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


class MainViewModel : ViewModel(){
    private val listGithubUsers = MutableLiveData<ArrayList<GithubUsers?>>()
    private val userRepo = GithubUserRepo()


    fun returnUserSearch(userKeyWord: String) {
        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                val arrayGithubUsers = ArrayList<GithubUsers?>()
                val users = userRepo.getService().returnUserSearch(userKeyWord, "followers").items
                Log.d("success", "Request Berhasil")
                try {
                    if (users != null) {
                        for (user in users) {
                            val userDetail =  userRepo.getService().returnUserDetail(user?.login.toString())
                            user?.name = userDetail?.name
                            user?.publicRepos = userDetail?.publicRepos
                            user?.followers = userDetail?.followers
                            user?.following = userDetail?.following
                            arrayGithubUsers.add(user)
                        }
                    }
                    listGithubUsers.postValue(arrayGithubUsers)
                } catch (throwable: Throwable) {
                    when(throwable){
                        is IOException -> Log.d("IOException", "Network Error")
                        is HttpException -> Log.d("HttpException", throwable.message().toString())
                        else -> Log.d("UnknownError", "Unknown Error")
                    }
                }
            }

        }
    }


    fun getUsers(): LiveData<ArrayList<GithubUsers?>> {
        return listGithubUsers
    }
}


