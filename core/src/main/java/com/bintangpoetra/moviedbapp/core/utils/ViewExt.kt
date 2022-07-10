package com.bintangpoetra.moviedbapp.core.utils

import android.widget.ImageView
import com.bintangpoetra.moviedbapp.core.BuildConfig
import com.bumptech.glide.Glide

fun ImageView.setImageFromUrl(url: String) {
    Glide.with(this.context)
        .load(BuildConfig.IMAGE_BASE_URL + url)
        .into(this)
}