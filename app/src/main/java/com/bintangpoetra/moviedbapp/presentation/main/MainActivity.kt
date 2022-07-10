package com.bintangpoetra.moviedbapp.presentation.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.bintangpoetra.moviedbapp.R
import com.bintangpoetra.moviedbapp.core.data.lib.ApiResponse
import com.bintangpoetra.moviedbapp.core.presentation.adapter.MovieCartAdapter
import com.bintangpoetra.moviedbapp.core.presentation.adapter.MovieHorizontalAdapter
import com.bintangpoetra.moviedbapp.databinding.ActivityMainBinding
import com.bintangpoetra.moviedbapp.presentation.detail.DetailActivity
import com.bintangpoetra.moviedbapp.presentation.list.ListActivity
import com.bintangpoetra.moviedbapp.presentation.search.SearchActivity
import com.bintangpoetra.moviedbapp.utils.CategoryType.PLAYING
import com.bintangpoetra.moviedbapp.utils.CategoryType.POPULAR
import com.bintangpoetra.moviedbapp.utils.ext.gone
import com.bintangpoetra.moviedbapp.utils.ext.show
import com.bintangpoetra.moviedbapp.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var _activityMainBinding: ActivityMainBinding
    private val binding get() = _activityMainBinding

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  

        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_activityMainBinding.root)

        initUI()
        initObserver()
        initAction()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSearch -> {
                SearchActivity.start(this)
                true
            }
            R.id.menuFavorite -> {
                val uri = Uri.parse("moviefavorite://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvUpcomingMovie)
    }

    private fun initAction() {
        binding.apply {
            btnSeeMorePlayingNow.setOnClickListener {
                ListActivity.start(this@MainActivity, PLAYING)
            }
            btnSeeMorePopularMovie.setOnClickListener {
                ListActivity.start(this@MainActivity, POPULAR)
            }
        }
    }

    private fun initObserver() {
        mainViewModel.getUpcomingMovies().observe(this) { result ->
            when (result) {
                is ApiResponse.Loading -> {
                    isLoading(true)
                }
                is ApiResponse.Success -> {
                    val movieCartAdapter = MovieCartAdapter(
                        result.data,
                        onClick = {
                            DetailActivity.start(this, it)
                        }
                    )
                    binding.rvUpcomingMovie.apply {
                        adapter = movieCartAdapter
                        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    }
                    isLoading(false)
                }
                is ApiResponse.Empty -> {
                    isLoading(false)
                    showToast("Movie list is empty...")
                }
                is ApiResponse.Error -> {
                    isLoading(false)
                    showToast(result.errorMessage)
                }
            }
        }
        mainViewModel.getPopularMovies().observe(this) { result ->
            when (result) {
                is ApiResponse.Loading -> {
                    isLoading(true)
                }
                is ApiResponse.Success -> {
                    val movieHorizontalAdapter = MovieHorizontalAdapter(
                        result.data,
                        onClick = {
                            DetailActivity.start(this, it)
                        }
                    )
                    binding.rvPopularMovie.apply {
                        adapter = movieHorizontalAdapter
                        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    }
                    isLoading(false)
                }
                is ApiResponse.Empty -> {
                    isLoading(false)
                    showToast("Movie list is empty...")
                }
                is ApiResponse.Error -> {
                    isLoading(false)
                    showToast(result.errorMessage)
                }
            }
        }
        mainViewModel.getPlayingMovies().observe(this) { result ->
            when (result) {
                is ApiResponse.Loading -> {
                    isLoading(true)
                }
                is ApiResponse.Success -> {
                    val movieHorizontalAdapter = MovieHorizontalAdapter(
                        result.data,
                        onClick = {
                            DetailActivity.start(this, it)
                        }
                    )
                    binding.rvPlayingNow.apply {
                        adapter = movieHorizontalAdapter
                        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    }
                    isLoading(false)
                }
                is ApiResponse.Empty -> {
                    isLoading(false)
                    showToast("Movie list is empty...")
                }
                is ApiResponse.Error -> {
                    isLoading(false)
                    showToast(result.errorMessage)
                }
            }
        }
    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.apply {
                shimmerLoading.show()
                shimmerLoading.startShimmer()
            }
        } else {
            binding.shimmerLoading.gone()
            binding.mainLayout.show()
        }
    }

}