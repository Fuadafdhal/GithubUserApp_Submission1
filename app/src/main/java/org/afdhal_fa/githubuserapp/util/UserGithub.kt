package org.afdhal_fa.githubuserapp.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserGithub(
    val name: String,
    val username: String,
    val photo: String,
    val company: String,
    val location: String,
    val repository: Int,
    val follower: Int,
    val following: Int

) : Parcelable