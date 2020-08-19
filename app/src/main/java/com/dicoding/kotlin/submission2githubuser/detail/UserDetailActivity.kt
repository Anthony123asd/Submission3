package com.dicoding.kotlin.submission2githubuser.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.kotlin.submission2githubuser.R
import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val githubUser = intent.getParcelableExtra(EXTRA_USER) as GithubUsers

        Glide.with(this.applicationContext).load(githubUser.avatarUrl).into(circleImageView)
        detail_name.text = githubUser.name
        detail_login.text = githubUser.login
        detail_description.text =
            """followers: ${githubUser.followers} following: ${githubUser.following} public repos: ${githubUser.publicRepos}"""

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        sectionsPagerAdapter.username = githubUser.login.toString()
        view_pager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(view_pager)
        //supportActionBar?.elevation = 0f
    }
}