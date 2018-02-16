package com.troncodroide.themoviedb.ui.uitls

import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide

/**
 * Created by DTron on 13/02/2018.
 */

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load("http://image.tmdb.org/t/p/w185/" + url).into(this)
}

fun View.setCompactColor(@ColorRes color: Int) {
    setBackgroundColor(ContextCompat.getColor(context, color))
}

fun View.setMargin(padding: Int) {
    if (layoutParams == null) {
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        (layoutParams as LinearLayout.LayoutParams).bottomMargin = padding
        (layoutParams as LinearLayout.LayoutParams).topMargin = padding
        (layoutParams as LinearLayout.LayoutParams).marginEnd = padding
    }
    requestLayout()
}

fun View.setPadding(padding: Int) {
    setPaddingRelative(padding, padding, padding, padding)
    requestLayout()
}