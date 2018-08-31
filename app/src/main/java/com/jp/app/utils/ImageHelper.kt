package com.jp.app.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object ImageHelper {

    @JvmStatic
    fun loadImage(context: Context, uri: Uri, imageView: ImageView) {
        val requestBuilder = Glide.with(context)
                .load(uri)
                .transition(DrawableTransitionOptions.withCrossFade())
        requestBuilder.into(imageView)
    }


}
