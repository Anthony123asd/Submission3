package com.dicoding.kotlin.submission2githubuser.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.kotlin.submission2githubuser.GithubUser
import com.dicoding.kotlin.submission2githubuser.R
import kotlinx.android.synthetic.main.item_list.view.*

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>(){
    private val mData = ArrayList<GithubUser>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setData(users: ArrayList<GithubUser>) {
        mData.clear()
        mData.addAll(users)
        notifyDataSetChanged()
    }

    inner class DetailViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(githubUser: GithubUser) {
            with(itemView){
                Glide.with(itemView)
                    .load(githubUser.avatarURL)
                    .into(profile_image)
                user_name.text = githubUser.login

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return DetailViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: GithubUser)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}