package com.fetchcatsimages.util

import com.fetchcatsimages.model.Image

interface ResultResponse {
    fun onSuccess(imgList: List<Image>)
    fun onFailure(message: String)
}