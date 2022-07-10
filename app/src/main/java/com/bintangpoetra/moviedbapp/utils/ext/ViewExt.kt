package com.bintangpoetra.moviedbapp.utils.ext

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bintangpoetra.moviedbapp.BuildConfig
import com.bumptech.glide.Glide

fun ImageView.setImageFromUrl(context: Context, url: String) {
    Glide.with(context)
        .load(BuildConfig.IMAGE_BASE_URL + url)
        .centerCrop()
        .into(this)
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}