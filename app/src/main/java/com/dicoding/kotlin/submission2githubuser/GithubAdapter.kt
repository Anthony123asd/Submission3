package com.dicoding.kotlin.submission2githubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import kotlinx.android.synthetic.main.item_list.view.*

class GithubAdapter : RecyclerView.Adapter<GithubAdapter.GithubViewHolder>(){
    private val mData = ArrayList<GithubUsers?>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setData(users: ArrayList<GithubUsers?>) {
        mData.clear()
        mData.addAll(users)
        notifyDataSetChanged()
    }

    fun getData() = mData

    inner class GithubViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(githubUser: GithubUsers?) {
            with(itemView){
                Glide.with(itemView)
                    .load(githubUser?.avatarUrl)
                    .into(profile_image)
                user_name.text = githubUser?.login
                user_desc.text = "${githubUser?.followers} followers ${githubUser?.publicRepos} public repos"

                itemView.setOnClickListener{onItemClickCallback?.onItemClicked(githubUser)}
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

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun clearData() {
        mData.clear()
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: GithubUsers?)
    }
}
