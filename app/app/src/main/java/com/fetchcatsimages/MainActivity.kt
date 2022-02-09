package com.fetchcatsimages

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fetchcatsimages.model.Image
import com.fetchcatsimages.util.ResultResponse
import com.fetchcatsimages.viewmodel.ImageViewModel

class MainActivity : AppCompatActivity(), ResultResponse {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContentView(R.layout.activity_main)
    }

    private fun init() {
        ImageViewModel().getGalleryImages()
    }

    override fun onSuccess(imgList: List<Image>) {
        val TAG = "MyActivity"
        Log.i(TAG, "MyClass.getView() — get item number")
    }

    override fun onFailure(message: String) {
        TODO("Not yet implemented")
    }
}