package com.example.pexelsapp.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pexelsapp.PexelsApp
import com.example.pexelsapp.R
import com.example.pexelsapp.domain.GetImagesUsecase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var imageView: ImageView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.editText)

        val viewModel =
            PexelsViewModel(GetImagesUsecase((application as PexelsApp).appComponent.repo()))

        button.setOnClickListener {
            val query = editText.text.toString()
            viewModel.loadImage(query, 1)
        }

        lifecycleScope.launch {
            viewModel.images
                .collect {
                    Glide.with(this@MainActivity)
                        .load(it.photos?.first()?.source?.link)
                        .into(imageView)
                }
        }
        viewModel.loadImage("sea", 1)
    }
}