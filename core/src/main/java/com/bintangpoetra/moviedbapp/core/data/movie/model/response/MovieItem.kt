package com.bintangpoetra.moviedbapp.core.data.movie.model.response

import com.google.gson.annotations.SerializedName

data class MovieItem(

    @field:SerializedName("adult")
    val adult: Boolean,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("id")
    val id: Int,
)
