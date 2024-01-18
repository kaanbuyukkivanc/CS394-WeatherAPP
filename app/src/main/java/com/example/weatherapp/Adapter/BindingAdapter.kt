package com.example.weatherapp.Adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.weatherapp.R

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, photo: Int) {
    imageView.setImageResource(photo)
}



