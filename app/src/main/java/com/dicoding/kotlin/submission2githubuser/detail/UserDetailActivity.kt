package com.dicoding.kotlin.submission2githubuser.detail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.kotlin.submission2githubuser.R
import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.activity_user_detail.progressBar
import kotlinx.android.synthetic.main.activity_user_detail.view.*

class UserDetailActivity : AppCompatActivity() {
    private lateinit var detailAdapter : DetailAdapter
    private lateinit var userDetailViewModel : UserDetailViewModel
    companion object{
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        //userDetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserDetailViewModel::class.java)

        val githubUser = intent.getParcelableExtra(EXTRA_USER) as GithubUsers
        //userDetailViewModel.setData(githubUser)
        Glide.with(this.applicationContext).load(githubUser.avatarUrl).into(circleImageView)
        detail_name.text = githubUser.name
        detail_login.text = githubUser.login
        detail_description.text =
            """followers: ${githubUser.followers} following: ${githubUser.following} public repos: ${githubUser.publicRepos}"""
        /*
        detailAdapter = DetailAdapter()
        detailAdapter.notifyDataSetChanged()

        userDetailViewModel.requestUserFollowers().observe(this, Observer { followers->
            if (followers != null) {
                detailAdapter.setData(followers)
                showLoading(false)
            }
        })

         */

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(view_pager)

        //supportActionBar?.elevation = 0f
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        /*val githubUser = userDetailViewModel.getUserDetail().value
        if (parent != null) {
            Glide.with(parent)
                .load(githubUser?.avatarUrl)
                .into(circleImageView)
            user_name.text = githubUser?.name
            user_desc.text = """${githubUser?.followers} followers
                ${githubUser?.following} following
                ${githubUser?.publicRepos} public repos"""
        }
*/
        return super.onCreateView(parent, name, context, attrs)

    }
}