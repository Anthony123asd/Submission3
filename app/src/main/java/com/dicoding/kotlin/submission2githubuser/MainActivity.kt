package com.dicoding.kotlin.submission2githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.kotlin.submission2githubuser.detail.UserDetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var GithubAdapter: GithubAdapter
    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GithubAdapter = GithubAdapter()
        GithubAdapter.notifyDataSetChanged()

        search_results.layoutManager = LinearLayoutManager(this)
        search_results.adapter = GithubAdapter

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        search_button.setOnClickListener(){
            val searchText = edit_search.text.toString()
            if (searchText.isEmpty()) return@setOnClickListener
            showLoading(true)
            mainViewModel.returnUserSearch(searchText)
        }

        mainViewModel.getUsers().observe(this, Observer { users->
            if (users != null) {
                GithubAdapter.setData(users)
                showLoading(false)
            }
        })

        GithubAdapter.setOnItemClickCallback(object : GithubAdapter.OnItemClickCallback{
            override fun onItemClicked(user: GithubUser) {
                val toDetailIntent = Intent(this@MainActivity, UserDetailActivity::class.java)
                toDetailIntent.putExtra(UserDetailActivity.EXTRA_USER, user)
            }

        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        }else {
            progressBar.visibility = View.GONE
        }
    }
}