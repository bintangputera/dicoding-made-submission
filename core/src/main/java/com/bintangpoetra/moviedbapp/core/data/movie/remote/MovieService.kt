package com.bintangpoetra.moviedbapp.core.data.movie.remote

import com.bintangpoetra.moviedbapp.core.BuildConfig
import com.bintangpoetra.moviedbapp.core.data.lib.MovieApiResponse
import com.bintangpoetra.moviedbapp.core.data.movie.model.response.GetCastResponse
import com.bintangpoetra.moviedbapp.core.data.movie.model.response.MovieItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : MovieApiResponse<MovieItem>

    @GET("movie/now_playing")
    suspend fun getPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : MovieApiResponse<MovieItem>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : MovieApiResponse<MovieItem>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCasts(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): GetCastResponse

    @GET("search/movie")
    suspend fun getMovieByQuery(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieApiResponse<MovieItem>

}