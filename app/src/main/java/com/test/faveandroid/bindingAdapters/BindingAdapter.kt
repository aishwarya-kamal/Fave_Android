package com.test.faveandroid.bindingAdapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.test.faveandroid.R

object BindingAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindImage(imgView: ImageView, imgUrl: String?) {

        val completeUrl = "https://image.tmdb.org/t/p/w342$imgUrl"
        val imgUri = completeUrl.toUri()

        Glide.with(imgView.context)
            .load(imgUri)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_error_image)
            .into(imgView)

    }
}