package com.dicoding.kotlin.submission2githubuser.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.dicoding.kotlin.submission2githubuser.db.UserFavoriteContract.UserFavoriteColumns.Companion.ID_
import com.dicoding.kotlin.submission2githubuser.db.UserFavoriteContract.UserFavoriteColumns.Companion.TABLE_NAME
import java.sql.SQLException

class UserFavoriteHelper(context: Context) {
    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var databaseHelper: DatabaseHelper
        private var INSTANCE: UserFavoriteHelper? = null

        fun getInstance(context: Context): UserFavoriteHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserFavoriteHelper(context)
            }

        private lateinit var database: SQLiteDatabase
    }

    init {
        databaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLException::class)
    fun open(){
        database = databaseHelper.writableDatabase
    }

    fun close(){
        databaseHelper.close()

        if (database.isOpen) {
            database.close()
        }
    }

    fun queryALl(): Cursor{
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$ID_ ASC"
        )
    }

    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$ID_ = ?",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$ID_ = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$ID_ = $id",null)
    }
}