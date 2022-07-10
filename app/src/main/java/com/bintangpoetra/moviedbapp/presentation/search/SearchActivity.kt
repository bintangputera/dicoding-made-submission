package com.bintangpoetra.moviedbapp.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bintangpoetra.moviedbapp.core.data.lib.ApiResponse
import com.bintangpoetra.moviedbapp.core.presentation.adapter.FavoriteAdapter
import com.bintangpoetra.moviedbapp.databinding.ActivitySearchBinding
import com.bintangpoetra.moviedbapp.presentation.detail.DetailActivity
import com.bintangpoetra.moviedbapp.utils.ext.gone
import com.bintangpoetra.moviedbapp.utils.ext.hide
import com.bintangpoetra.moviedbapp.utils.ext.show
import com.bintangpoetra.moviedbapp.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity: AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SearchActivity::class.java))
        }
    }

    private lateinit var _activitySearchBinding: ActivitySearchBinding
    private val binding get() = _activitySearchBinding

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activitySearchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(_activitySearchBinding.root)

        initUI()
        initAction()
    }

    private fun initUI(){
        binding.shimmerLoading.hide()
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initAction() {
        binding.svUser.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                getSearchUser(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun getSearchUser(query: String) {
        searchViewModel.getMoviesByQuery(query).observe(this) { result ->
            when (result) {
                is ApiResponse.Loading -> {
                    binding.tvErrorMessage.hide()
                    isLoading(true)
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
                        layoutManager = LinearLayoutManager(this@SearchActivity)
                    }
                    binding.tvErrorMessage.hide()
                    isLoading(false)
                }
                is ApiResponse.Empty -> {
                    binding.tvErrorMessage.show()
                    isLoading(false)
                    showToast("Movie list is empty...")
                }
                is ApiResponse.Error -> {
                    binding.tvErrorMessage.show()
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
            binding.rvMovie.show()
        }
    }


}