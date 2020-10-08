package com.dicoding.kotlin.submission2githubuser.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dicoding.kotlin.submission2githubuser.db.UserFavoriteContract.UserFavoriteColumns
import com.dicoding.kotlin.submission2githubuser.db.UserFavoriteContract.UserFavoriteColumns.Companion.TABLE_NAME

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "user_favorite_db"
        private const val DATABASE_VERSION = 1
        private val SQL_CREATE_TABLE_USER_FAV = """
            CREATE TABLE ${UserFavoriteColumns.TABLE_NAME}
            (${UserFavoriteColumns.ID_} INTEGER PRIMARY KEY,
             ${UserFavoriteColumns.COLUMN_LOGIN} TEXT NOT NULL,
             ${UserFavoriteColumns.COLUMN_AVATAR_URL} TEXT,
             ${UserFavoriteColumns.COLUMN_PUBLIC_REPOS} INTEGER,
             ${UserFavoriteColumns.COLUMN_FOLLOWERS} INTEGER,
             ${UserFavoriteColumns.COLUMN_FOLLOWING} INTEGER,
             ${UserFavoriteColumns.COLUMN_NAME} TEXT)
        """.trimIndent()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_USER_FAV)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion0: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}