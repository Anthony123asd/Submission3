package com.dicoding.kotlin.submission2githubuser.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.kotlin.submission2githubuser.R
import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import kotlinx.android.synthetic.main.follow_list.view.*


class DetailAdapter: RecyclerView.Adapter<DetailAdapter.DetailViewHolder>(){
    private val followersData = ArrayList<GithubUsers?>()

    fun setData(followers: ArrayList<GithubUsers?>) {
        followersData.clear()
        followersData.addAll(followers)
        notifyDataSetChanged()
    }




    inner class DetailViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(githubUser: GithubUsers?) {
            with(itemView){
                Glide.with(itemView)
                    .load(githubUser?.avatarUrl)
                    .into(follow_image)
                follow_name.text = githubUser?.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.follow_list, parent, false)
        return DetailViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return followersData.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(followersData[position])
    }
}