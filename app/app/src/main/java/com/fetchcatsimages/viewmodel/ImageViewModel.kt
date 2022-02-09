package com.fetchcatsimages.viewmodel

import androidx.lifecycle.ViewModel
import com.fetchcatsimages.data.model.ImageData
import com.fetchcatsimages.data.repository.ImageRepository
import com.fetchcatsimages.model.Image
import com.fetchcatsimages.util.Coroutines
import com.fetchcatsimages.util.ResultResponse

class ImageViewModel : ViewModel() {

    private val resultResponse: ResultResponse? = null

    fun getGalleryImages() {
        Coroutines.main {
            val response = ImageRepository().galleryImages()
            if (response.isSuccessful) {
                response.body()?.data?.let {
                    val result = it.map { imgMap ->
                        convertData(imgMap)
                    }
                    resultResponse?.onSuccess(result)
                    return@main
                }
            } else
                resultResponse?.onFailure("Error Code: ${response.code()}")
        }

    }

    private fun convertData(imageData: ImageData): Image {
        return Image(
            id = imageData.id,
            title = imageData.title,
            description = imageData.images?.get(0)?.description,
            ups = imageData.ups,
            downs = imageData.downs,
            score = imageData.score,
            imageUrl = imageData.images?.get(0)?.link
        )
    }
}