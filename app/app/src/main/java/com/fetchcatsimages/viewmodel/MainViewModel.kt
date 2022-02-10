package com.fetchcatsimages.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fetchcatsimages.data.model.ImageData
import com.fetchcatsimages.data.repository.ImageRepository
import com.fetchcatsimages.model.Image
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val imageList = MutableLiveData<List<Image>>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getGalleryImages() {

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = ImageRepository().galleryImages()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        val result = it.map { imgMap ->
                            convertData(imgMap)
                        }
                        imageList.postValue(result)
                        loading.value = false
                    }
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun convertData(imageData: ImageData): Image {
        return Image(
            imageUrl = imageData.images?.get(0)?.link
        )
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}