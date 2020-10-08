package com.dicoding.kotlin.submission2githubuser.db

import android.net.Uri
import android.provider.BaseColumns

object UserFavoriteContract {
    const val AUTHORITY = "com.dicoding.submission3"
    const val SCHEME = "content"

     class UserFavoriteColumns : BaseColumns{
        companion object{
            const val TABLE_NAME = "favorite_users"
            const val ID_ = "id_"
            const val COLUMN_LOGIN = "login"
            const val COLUMN_AVATAR_URL = "avatar_url"
            const val COLUMN_PUBLIC_REPOS = "public_repos"
            const val COLUMN_FOLLOWERS = "followers"
            const val COLUMN_FOLLOWING = "following"
            const val COLUMN_NAME = "name"

            val CONTENT_URI : Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}