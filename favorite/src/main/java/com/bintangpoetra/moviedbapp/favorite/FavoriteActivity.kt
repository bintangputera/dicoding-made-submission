package com.bintangpoetra.moviedbapp.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bintangpoetra.moviedbapp.core.presentation.adapter.FavoriteAdapter
import com.bintangpoetra.moviedbapp.di.favoriteModule
import com.bintangpoetra.moviedbapp.favorite.databinding.ActivityFavoriteBinding
import com.bintangpoetra.moviedbapp.presentation.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var _activityFavoriteBinding: ActivityFavoriteBinding
    private val binding get() = _activityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(_activityFavoriteBinding.root)

        loadKoinModules(favoriteModule)

        initUI()
        initObservers()
    }

    private fun initUI() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initObservers() {
        favoriteViewModel.getAllFavoriteMovies().observe(this) {
            if (it.isEmpty()) {
                binding.rvFavoriteMovie.visibility = View.INVISIBLE
                binding.tvErrorMessage.visibility = View.VISIBLE
            } else {
                binding.rvFavoriteMovie.visibility = View.VISIBLE
                binding.tvErrorMessage.visibility = View.INVISIBLE
                val favoriteAdapter = FavoriteAdapter(it, onClick = { movie ->
                    DetailActivity.start(this, movie)
                })
                binding.rvFavoriteMovie.apply {
                    adapter = favoriteAdapter
                    layoutManager = LinearLayoutManager(this@FavoriteActivity)
                }
            }
        }
    }

}