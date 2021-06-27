package com.mokresh.tidalmusic.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("circleImageUrl")
fun ImageView.loadCircleImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this)
        .load(url)
        .circleCrop()
        .into(this);
}

@BindingAdapter("visibility")
fun setViewVisibility(view: View?, bool: Boolean) {
    if (view != null) view.visibility = if (bool) View.VISIBLE else View.GONE
}



