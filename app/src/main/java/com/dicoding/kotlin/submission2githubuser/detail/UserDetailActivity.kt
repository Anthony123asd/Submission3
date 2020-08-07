package com.dicoding.kotlin.submission2githubuser.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.kotlin.submission2githubuser.GithubUser
import com.dicoding.kotlin.submission2githubuser.MainViewModel
import com.dicoding.kotlin.submission2githubuser.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.activity_user_detail.progressBar

class UserDetailActivity : AppCompatActivity() {
    private lateinit var userDetailViewModel : UserDetailViewModel
    companion object{
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        showLoading(true)
        userDetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserDetailViewModel::class.java)

        val user = intent.getParcelableExtra(EXTRA_USER) as GithubUser

        userDetailViewModel.getUserDetail().observe(this, Observer { users->
            if (users != null) {
                DetailAdapter.setData(users)
                showLoading(false)
            }
        })

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        }else {
            progressBar.visibility = View.GONE
        }
    }
}