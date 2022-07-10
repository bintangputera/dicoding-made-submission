package com.bintangpoetra.moviedbapp.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bintangpoetra.moviedbapp.R
import com.bintangpoetra.moviedbapp.R.string
import com.bintangpoetra.moviedbapp.core.data.lib.ApiResponse
import com.bintangpoetra.moviedbapp.core.domain.model.Movie
import com.bintangpoetra.moviedbapp.core.presentation.adapter.CastAdapter
import com.bintangpoetra.moviedbapp.core.utils.setImageFromUrl
import com.bintangpoetra.moviedbapp.databinding.ActivityDetailBinding
import com.bintangpoetra.moviedbapp.utils.BundleKeys.MOVIE_BUNDLE
import com.bintangpoetra.moviedbapp.utils.ext.gone
import com.bintangpoetra.moviedbapp.utils.ext.show
import com.bintangpoetra.moviedbapp.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var _detailActivity: ActivityDetailBinding
    private val binding get() = _detailActivity

    private var movie: Movie? = null

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var castAdapter: CastAdapter

    private var isFavorite = false

    companion object {

        fun start(context: Context, movie: Movie) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_BUNDLE, movie)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _detailActivity = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(_detailActivity.root)

        initIntent()
        initUI()
        initObservers()
        initAction()
    }

    private fun initIntent() {
        movie = intent.getParcelableExtra(MOVIE_BUNDLE)
    }

    private fun initAction() {
        binding.fabFavorite.setOnClickListener {
            movie?.let { mov -> favoriteAction(mov) }
        }
    }

    private fun initUI() {
        setupToolbar()

        binding.apply {
            tvMovieTitle.text = movie?.title.toString()
            tvOverview.text = movie?.overview.toString()
            tvRating.text = movie?.voteAverage.toString()
            tvAdult.visibility = if (movie?.adult!!) View.VISIBLE else View.GONE
            imgThumbnail.setImageFromUrl(movie?.posterPath.toString())
        }
    }

    private fun initObservers() {
        detailViewModel.getMovieCasts(movie?.id!!).observe(this) { result ->
            when (result) {
                is ApiResponse.Loading -> {
                    isLoading(true)
                }
                is ApiResponse.Success -> {
                    castAdapter = CastAdapter(result.data)
                    binding.rvCast.apply {
                        adapter = castAdapter
                        layoutManager = LinearLayoutManager(this@DetailActivity)
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
        detailViewModel.checkIsFavoriteMovie(movie?.id!!).observe(this) { favorite ->
            binding.apply {
                isFavorite = favorite
                changeButtonImageResource(favorite)
            }
        }
    }

    private fun favoriteAction(movie: Movie) {
        if (isFavorite) {
            AlertDialog.Builder(this).apply {
                setTitle(getString(string.title_delete_favorite_dialog))
                title
                setMessage(getString(string.message_delete_favorite_dialog))
                setNegativeButton(getString(string.action_cancel)) { p0, _ ->
                    p0.dismiss()
                }
                setPositiveButton(getString(string.action_delete)) { _, _ ->
                    deleteFavorite(movie.id)
                    isFavorite = false
                    binding.fabFavorite.setImageResource(R.drawable.ic_favorite_border)
                }
            }.create().show()
        } else {
            addFavorite(movie)
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_menu)
            isFavorite = true
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun changeButtonImageResource(favorite: Boolean) {
        if (favorite) {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_menu)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun deleteFavorite(id: Int) {
        detailViewModel.deleteMovieFromFavorite(id)
        showToast("Success deleting user from favorite")
    }

    private fun addFavorite(movie: Movie) {
        detailViewModel.saveMovieAsFavorite(movie)
        showToast("Success adding user to favorite")
    }

    private fun isLoading(loading: Boolean) {
        if (loading) {
            binding.shimmerLoading.apply {
                show()
                startShimmer()
            }
        } else {
            binding.rvCast.show()
            binding.shimmerLoading.gone()
        }
    }
}