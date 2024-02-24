package com.me.rickmorty.util.tools

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("android:src")
fun loadImage(
    view: ImageView,
    imageUrl: String?
) {
    imageUrl?.let {
        Glide.with(view.context)
            .load(it)
            .into(view)
    }
}