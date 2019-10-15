package com.dev.mahmoud_ashraf.github_repo.utils


import android.content.Context
import android.widget.Toast
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by mahmoud_ashraf on 12,05,2019
 */

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun ImageView.loadImage(url: String) {
        Glide.with(this).load(url).into(this)
}
