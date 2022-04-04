package com.example.bluemarket.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoadingImpl:ImageLoading {
    override fun load(imageView: ImageView, imageUrl: String) {
        Picasso.get().load(imageUrl).into(imageView)
    }
}