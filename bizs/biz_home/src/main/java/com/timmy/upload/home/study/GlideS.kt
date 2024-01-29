package com.timmy.upload.home.study

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideS {

    fun glideS(activity: Activity, url: String, imageView: ImageView) {
        Glide.with(activity).load("https://goo.gl/gEgYUd").into(imageView);
    }
}