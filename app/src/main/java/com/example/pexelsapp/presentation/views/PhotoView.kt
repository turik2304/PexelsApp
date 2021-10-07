package com.example.pexelsapp.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.pexelsapp.R

class PhotoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val imageView: ImageView
    private val textView: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.item_pexels, this, true)
        gravity = Gravity.CENTER
        orientation = VERTICAL
        imageView = findViewById(R.id.imageViewPhoto)
        textView = findViewById(R.id.textViewDescription)
    }
}