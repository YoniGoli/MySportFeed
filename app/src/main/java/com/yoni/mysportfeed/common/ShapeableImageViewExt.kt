package com.yoni.mysportfeed.common

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.imageview.ShapeableImageView

fun ShapeableImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .transition(
            DrawableTransitionOptions.withCrossFade()
        )
        .fitCenter()
        .into(this)
}