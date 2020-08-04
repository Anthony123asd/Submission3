package com.dicoding.kotlin.submission2githubuser

import android.os.Parcel
import android.os.Parcelable

data class GithubUser (
    var id: Int? = 0,
    var name : String? = "nama_user",
    var login : String? = "login_user",
    var url : String? = "https://api.github.com/users/$login",
    var avatarURL : String? = "https://avatars2.githubusercontent.com/u/",
    var followers : Int? = 0,
    var publicRepos : Int? = 0
) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(login)
        parcel.writeString(url)
        parcel.writeString(avatarURL)
        parcel.writeValue(followers)
        parcel.writeValue(publicRepos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GithubUser> {
        override fun createFromParcel(parcel: Parcel): GithubUser {
            return GithubUser(parcel)
        }

        override fun newArray(size: Int): Array<GithubUser?> {
            return arrayOfNulls(size)
        }
    }
}