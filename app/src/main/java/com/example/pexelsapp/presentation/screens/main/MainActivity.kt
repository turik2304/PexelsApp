package com.example.pexelsapp.presentation.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pexelsapp.R
import com.example.pexelsapp.presentation.screens.search_photo.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, SearchFragment())
                .commit()
        }
    }
}