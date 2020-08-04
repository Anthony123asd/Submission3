package com.dicoding.kotlin.submission2githubuser.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.kotlin.submission2githubuser.GithubUser
import com.dicoding.kotlin.submission2githubuser.R
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val user = intent.getParcelableExtra(EXTRA_USER) as GithubUser

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f
    }
}