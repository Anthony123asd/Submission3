package com.dicoding.kotlin.submission2githubuser.db

import android.database.Cursor
import com.dicoding.kotlin.submission2githubuser.data.GithubUsers

object MappingHelper {

    fun mapCursorToArrayList(cursor : Cursor?): ArrayList<GithubUsers?>{
        val githubUsersList = ArrayList<GithubUsers?>()

        cursor?.apply {
            while (moveToNext()) {
                val githubUsers = GithubUsers()
                githubUsers.id = getInt(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.ID_))
                githubUsers.login = getString(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.COLUMN_LOGIN))
                githubUsers.avatarUrl = getString(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.COLUMN_AVATAR_URL))
                githubUsers.publicRepos = getInt(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.COLUMN_PUBLIC_REPOS))
                githubUsers.followers = getInt(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.COLUMN_FOLLOWERS))
                githubUsers.following = getInt(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.COLUMN_FOLLOWING))
                githubUsers.name = getString(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.COLUMN_NAME))
                githubUsersList.add(githubUsers)
            }
        }
        cursor?.close()
        return githubUsersList
    }

    fun mapCursorToObject(cursor : Cursor?): GithubUsers?{
        val githubUsers = GithubUsers()

        cursor?.apply {
            moveToFirst()
            githubUsers.id = getInt(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.ID_))
            githubUsers.login = getString(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.COLUMN_LOGIN))
            githubUsers.avatarUrl = getString(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.COLUMN_AVATAR_URL))
            githubUsers.publicRepos = getInt(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.COLUMN_PUBLIC_REPOS))
            githubUsers.followers = getInt(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.COLUMN_FOLLOWERS))
            githubUsers.following = getInt(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.COLUMN_FOLLOWING))
            githubUsers.name = getString(getColumnIndexOrThrow(UserFavoriteContract.UserFavoriteColumns.COLUMN_NAME))
        }
        cursor?.close()
        return githubUsers
    }
}