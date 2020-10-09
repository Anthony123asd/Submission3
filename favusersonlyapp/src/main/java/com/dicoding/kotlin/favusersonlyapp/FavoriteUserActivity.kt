package com.dicoding.kotlin.favusersonlyapp

import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.kotlin.favusersonlyapp.data.GithubUsers
import com.dicoding.kotlin.favusersonlyapp.db.MappingHelper
import com.dicoding.kotlin.favusersonlyapp.db.UserFavoriteContract.UserFavoriteColumns.Companion.CONTENT_URI
import kotlinx.android.synthetic.main.activity_favorite_user.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteUserActivity : AppCompatActivity() {
    private lateinit var githubAdapter: GithubAdapter

    companion object{
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_user)

        supportActionBar?.title = resources.getString(R.string.app_name)
        rv_favorite_users.layoutManager = LinearLayoutManager(this)
        rv_favorite_users.setHasFixedSize(true)
        githubAdapter = GithubAdapter()
        rv_favorite_users.adapter = githubAdapter


        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        val myObserver = object : ContentObserver(handler) {
            override fun onChange(self: Boolean) {
                loadFavUserAsync()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)


        if(savedInstanceState == null){
            loadFavUserAsync()
        }else{
            val list = savedInstanceState.getParcelableArrayList<GithubUsers>(EXTRA_STATE)
            if (list != null) {
                githubAdapter.setData(list)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putParcelableArrayList(EXTRA_STATE, githubAdapter.getData() )
    }

    private fun loadFavUserAsync() {
        GlobalScope.launch(Dispatchers.IO){
            showLoading(true)
            val defferedUser = async {
                val cursor = contentResolver?.query(CONTENT_URI,null,null,null,null)
                MappingHelper.mapCursorToArrayList(cursor)
            }
            showLoading(false)
            val githubUserList = defferedUser.await()
            if (githubUserList.size > 0) {
                empty.visibility = View.INVISIBLE
                githubAdapter.setData(githubUserList)
            } else {
                empty.visibility = View.VISIBLE
                githubAdapter.clearData()
            }
        }
    }

    fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        }else {
            progressBar.visibility = View.GONE
        }
    }
}