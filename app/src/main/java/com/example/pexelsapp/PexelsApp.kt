package com.example.pexelsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PexelsApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}