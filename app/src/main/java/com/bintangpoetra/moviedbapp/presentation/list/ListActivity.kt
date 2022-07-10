package com.bintangpoetra.moviedbapp.presentation.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bintangpoetra.moviedbapp.R.string
import com.bintangpoetra.moviedbapp.core.data.lib.ApiResponse
import com.bintangpoetra.moviedbapp.core.presentation.adapter.FavoriteAdapter
import com.bintangpoetra.moviedbapp.databinding.ActivityListBinding
import com.bintangpoetra.moviedbapp.presentation.detail.DetailActivity
import com.bintangpoetra.moviedbapp.utils.BundleKeys.CATEGORY_LIST
import com.bintangpoetra.moviedbapp.utils.CategoryType.PLAYING
import com.bintangpoetra.moviedbapp.utils.CategoryType.POPULAR
import com.bintangpoetra.moviedbapp.utils.ext.hide
import com.bintangpoetra.moviedbapp.utils.ext.show
import com.bintangpoetra.moviedbapp.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private lateinit var _activityListBinding: ActivityListBinding
    private val binding get() = _activityListBinding

    private val listViewModel: ListViewModel by viewModel()

    companion object {
        fun start(context: Context, categoryType: String) {
            val intent = Intent(context, ListActivity::class.java)
            intent.putExtra(CATEGORY_LIST, categoryType)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityListBinding = ActivityListBinding.inflate(layoutInflater)
        setContentView(_activityListBinding.root)

        initUI()

        when(intent.getStringExtra(CATEGORY_LIST).toString()) {
            POPULAR -> {
                getPopularMovies()
            }
            PLAYING -> {
                getPlayingMovies()
            }
        }
    }

    private fun initUI(){
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun getPopularMovies() {
        binding.toolbar.title = getString(string.title_popular_movie)
        listViewModel.getPopularMovies().observe(this) { result ->
            when (result) {
                is ApiResponse.Loading -> {
                    binding.tvErrorMessage.hide()
                    showToast("Loading.......")
                }
                is ApiResponse.Success -> {
                    val movieAdapter = FavoriteAdapter(
                        result.data,
                        onClick = {
                            DetailActivity.start(this, it)
                        }
                    )
                    binding.rvMovie.apply {
                        adapter = movieAdapter
                        layoutManager = LinearLayoutManager(this@ListActivity)
                    }
                    binding.tvErrorMessage.hide()
                }
                is ApiResponse.Empty -> {
                    binding.tvErrorMessage.show()
                    showToast("Movie list is empty...")
                }
                is ApiResponse.Error -> {
                    binding.tvErrorMessage.show()
                    showToast(result.errorMessage)
                }
            }
        }
    }

    private fun getPlayingMovies() {
        binding.toolbar.title = getString(string.title_playing_now)
        listViewModel.getPlayingMovies().observe(this) { result ->
            when (result) {
                is ApiResponse.Loading -> {
                    binding.tvErrorMessage.hide()
                    showToast("Loading.......")
                }
                is ApiResponse.Success -> {
                    val movieAdapter = FavoriteAdapter(
                        result.data,
                        onClick = {
                            DetailActivity.start(this, it)
                        }
                    )
                    binding.rvMovie.apply {
                        adapter = movieAdapter
                        layoutManager = LinearLayoutManager(this@ListActivity)
                    }
                    binding.tvErrorMessage.hide()
                }
                is ApiResponse.Empty -> {
                    binding.tvErrorMessage.show()
                    showToast("Movie list is empty...")
                }
                is ApiResponse.Error -> {
                    binding.tvErrorMessage.show()
                    showToast(result.errorMessage)
                }
            }
        }
    }

}