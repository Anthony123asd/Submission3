package com.dicoding.kotlin.submission2githubuser.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.kotlin.submission2githubuser.R
import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import kotlinx.android.synthetic.main.follow_list.view.*


class FollowersAdapter: RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder>(){
    private val followersData = ArrayList<GithubUsers?>()

    fun setData(followers: ArrayList<GithubUsers?>) {
        followersData.clear()
        followersData.addAll(followers)
        notifyDataSetChanged()
    }




    class FollowersViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(githubUser: GithubUsers?) {
            with(itemView){
                Glide.with(itemView)
                    .load(githubUser?.avatarUrl)
                    .into(follow_image)
                follow_name.text = githubUser?.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.follow_list, parent, false)
        return FollowersViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return followersData.size
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        holder.bind(followersData[position])
    }
}