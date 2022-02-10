package com.fetchcatsimages

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fetchcatsimages.databinding.ActivityMainBinding
import com.fetchcatsimages.model.Image
import com.fetchcatsimages.ui.adapter.ImageAdapter
import com.fetchcatsimages.viewmodel.MainViewModel
import com.fetchcatsimages.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private var imageAdapter = ImageAdapter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observe()
        setRecyclerView()
        setContentView(binding.root)
    }

    private fun setRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this, STAGGERED_GRID_COUNT)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.adapter = imageAdapter
    }

    private fun observe() {
        viewModel.imageList.observe(this) {
            setMovies(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.getGalleryImages()
    }

    private fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(MainViewModel::class.java)
    }

    private fun setMovies(imgList: List<Image>) {
        imageAdapter.setItems(imgList)
    }

    companion object {
        const val STAGGERED_GRID_COUNT = 4
    }

}