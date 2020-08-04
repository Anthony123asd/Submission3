package com.dicoding.kotlin.submission2githubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list.view.*

class GithubAdapter : RecyclerView.Adapter<GithubAdapter.GithubViewHolder>(){
    private val mData = ArrayList<GithubUser>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setData(users: ArrayList<GithubUser>) {
        mData.clear()
        mData.addAll(users)
        notifyDataSetChanged()
    }

    inner class GithubViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(githubUser: GithubUser) {
            with(itemView){
                Glide.with(itemView)
                    .load(githubUser.avatarURL)
                    .into(profile_image)
                user_name.text = githubUser.login
                user_desc.text = "${githubUser.followers} followers    ${githubUser.publicRepos} public repos"

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return GithubViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: GithubUser)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}