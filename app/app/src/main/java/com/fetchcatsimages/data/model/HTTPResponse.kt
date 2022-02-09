package com.fetchcatsimages.data.model

import com.google.gson.annotations.SerializedName

data class HTTPResponse(

    @SerializedName("data")
    var data: List<ImageData>? = null,

    @SerializedName("success")
    var success: Boolean? = null,

    @SerializedName("status")
    var status: Int? = null
)