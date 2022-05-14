package com.example.idus.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setUrlImage")
    fun setUrlImage(imageView: ImageView, url: String?) {
        url ?: return
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}