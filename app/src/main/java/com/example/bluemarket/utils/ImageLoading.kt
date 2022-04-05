package com.example.bluemarket.utils

import android.widget.ImageView

interface ImageLoading {
    fun load(imageView: ImageView, imageUrl: String)
}