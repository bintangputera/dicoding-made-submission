package com.bintangpoetra.moviedbapp.core.data.movie.model.response

import com.google.gson.annotations.SerializedName

data class GetCastResponse(

    @field:SerializedName("cast")
    val cast: List<CastItem>,

    @field:SerializedName("id")
    val id: Int
)

data class CastItem(

    @field:SerializedName("character")
    val character: String?,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("profile_path")
    val profilePath: String?,

    @field:SerializedName("id")
    val id: Int,
)
