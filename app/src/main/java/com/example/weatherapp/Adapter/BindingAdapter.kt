package com.example.weatherapp.Adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, photo: Int) {
    imageView.setImageResource(photo)
}



