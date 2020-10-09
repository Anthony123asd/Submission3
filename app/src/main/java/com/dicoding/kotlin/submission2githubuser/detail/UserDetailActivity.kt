package com.dicoding.kotlin.submission2githubuser.detail

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import com.bumptech.glide.Glide
import com.dicoding.kotlin.submission2githubuser.R
import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import com.dicoding.kotlin.submission2githubuser.db.UserFavoriteContract.UserFavoriteColumns.Companion.COLUMN_AVATAR_URL
import com.dicoding.kotlin.submission2githubuser.db.UserFavoriteContract.UserFavoriteColumns.Companion.COLUMN_FOLLOWERS
import com.dicoding.kotlin.submission2githubuser.db.UserFavoriteContract.UserFavoriteColumns.Companion.COLUMN_FOLLOWING
import com.dicoding.kotlin.submission2githubuser.db.UserFavoriteContract.UserFavoriteColumns.Companion.COLUMN_LOGIN
import com.dicoding.kotlin.submission2githubuser.db.UserFavoriteContract.UserFavoriteColumns.Companion.COLUMN_NAME
import com.dicoding.kotlin.submission2githubuser.db.UserFavoriteContract.UserFavoriteColumns.Companion.COLUMN_PUBLIC_REPOS
import com.dicoding.kotlin.submission2githubuser.db.UserFavoriteContract.UserFavoriteColumns.Companion.CONTENT_URI
import com.dicoding.kotlin.submission2githubuser.db.UserFavoriteContract.UserFavoriteColumns.Companion.ID_
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {
    private var githubUserLogin : String? = null
    private var statusFavorite : Boolean = false

    companion object{
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val githubUser = intent.getParcelableExtra<GithubUsers>(EXTRA_USER)
        val uriWithId = Uri.parse(CONTENT_URI.toString()+"/"+ githubUser?.id)

        Glide.with(this.applicationContext).load(githubUser?.avatarUrl).into(circleImageView)
        detail_name.text = githubUser?.name
        detail_login.text = githubUser?.login
        detail_description.text =
            """followers: ${githubUser?.followers} following: ${githubUser?.following} public repos: ${githubUser?.publicRepos}"""
        githubUserLogin = githubUser?.login

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        sectionsPagerAdapter.username = githubUser?.login.toString()
        view_pager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(view_pager)
        //supportActionBar?.elevation = 0f

        val cursor = contentResolver.query(uriWithId, null, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                statusFavorite = true
            }
            cursor.close()
        }
        setStatusFavorite(statusFavorite)

        status_favorite.setOnClickListener{
            val values = contentValuesOf(
                ID_ to githubUser?.id,
                COLUMN_LOGIN to githubUser?.login,
                COLUMN_AVATAR_URL to githubUser?.avatarUrl,
                COLUMN_PUBLIC_REPOS to githubUser?.publicRepos,
                COLUMN_FOLLOWERS to githubUser?.followers,
                COLUMN_FOLLOWING to githubUser?.following,
                COLUMN_NAME to githubUser?.name
            )

            if (statusFavorite) {
                contentResolver.delete(uriWithId,null,null)
                Toast.makeText(this, "${githubUserLogin} batal difavoritkan", Toast.LENGTH_SHORT).show()
            }
            else {
                contentResolver.insert(CONTENT_URI, values)
                Toast.makeText(this, "${githubUserLogin} telah difavoritkan", Toast.LENGTH_SHORT).show()
            }

            statusFavorite = !statusFavorite
            setStatusFavorite(statusFavorite)
        }

    }

    private fun setStatusFavorite(status: Boolean) {
        if (status) {
            status_favorite.setImageResource(R.drawable.ic_baseline_favorite_on)
        } else {
            status_favorite.setImageResource(R.drawable.ic_baseline_favorite_off)
        }
    }
}