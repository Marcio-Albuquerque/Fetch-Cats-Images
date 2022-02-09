package com.fetchcatsimages.data.repository

import com.fetchcatsimages.data.model.HTTPResponse
import com.fetchcatsimages.data.service.api.ImgurService
import retrofit2.Response

class ImageRepository {

    suspend fun galleryImages(): Response<HTTPResponse> {
        val imgurService = ImgurService()
        return imgurService.getAllImages()
    }
}