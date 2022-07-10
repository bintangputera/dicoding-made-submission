package com.bintangpoetra.moviedbapp.core.domain.mapper

import com.bintangpoetra.moviedbapp.core.domain.model.Cast
import com.bintangpoetra.moviedbapp.core.domain.model.Movie
import com.bintangpoetra.moviedbapp.core.data.movie.local.room.MovieEntity
import com.bintangpoetra.moviedbapp.core.data.movie.model.response.CastItem
import com.bintangpoetra.moviedbapp.core.data.movie.model.response.MovieItem

fun MovieEntity.mapToMovie(): Movie =
    Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        adult = this.adult
    )

fun MovieItem.mapToMovie(): Movie =
    Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        adult = this.adult
    )

fun Movie.mapToMovieEntity(): MovieEntity =
    MovieEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath.toString(),
        voteAverage = this.voteAverage,
        adult = this.adult
    )

fun CastItem.mapToCast(): Cast =
    Cast(
        id = this.id,
        name = this.name,
        character = this.character,
        profilePath = this.profilePath
    )