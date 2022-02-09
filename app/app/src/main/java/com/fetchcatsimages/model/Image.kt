package com.fetchcatsimages.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(

    @SerializedName("id")
    var id: String = "",

    var title: String? = null,
    var description: String? = null,
    var ups: Int? = null,
    var downs: Int? = null,
    var score: Int? = null,
    var imageUrl: String? = null
) : Parcelable