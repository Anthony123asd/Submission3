package com.dicoding.kotlin.submission2githubuser.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import com.dicoding.kotlin.submission2githubuser.utils.GithubUserRepo
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception

class UserDetailViewModel : ViewModel() {
    private val githubUserDetail = MutableLiveData<GithubUsers>()
    private val githubUserFollowers = MutableLiveData<ArrayList<GithubUsers?>>()
    private val githubUserFollowings = MutableLiveData<ArrayList<GithubUsers?>>()
    private val userRepo = GithubUserRepo()

    fun setData(user: GithubUsers) {
        githubUserDetail.postValue(user)
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val followers =
                        async { userRepo.getService().returnFollowersList(user?.login.toString()) }
                    val followings =
                        async { userRepo.getService().returnFollowingList(user?.login.toString()) }
                    githubUserFollowers.postValue(followers.await())
                    githubUserFollowings.postValue(followings.await())
                } catch (exception: Exception) {
                    Log.e("TAG", exception.message)
                }


            }
        }
    }

    fun getUserDetail(): MutableLiveData<GithubUsers> {
        return githubUserDetail
    }

    fun requestUserFollowers(): LiveData<ArrayList<GithubUsers?>> {
        return githubUserFollowers
    }

    fun requestUserFollowings(): LiveData<ArrayList<GithubUsers?>> {
        return githubUserFollowings
    }
}