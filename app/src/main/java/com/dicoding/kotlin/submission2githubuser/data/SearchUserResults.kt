package com.dicoding.kotlin.submission2githubuser.data

import com.google.gson.annotations.SerializedName

data class SearchUserResults(
    @field:SerializedName("total_count")
    val totalCount: Int? = null,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @field:SerializedName("items")
    var items: List<GithubUsers?>? = null
    )
