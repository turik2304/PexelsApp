package com.example.pexelsapp

import android.app.Application
import com.example.pexelsapp.di.components.AppComponent
import com.example.pexelsapp.di.components.DaggerAppComponent
import com.example.pexelsapp.di.components.DaggerSearchComponent
import com.example.pexelsapp.di.components.SearchComponent

class PexelsApp : Application() {

    lateinit var appComponent: AppComponent
    lateinit var searchComponent: SearchComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create().apply {
            inject(this@PexelsApp)
        }

        searchComponent = DaggerSearchComponent
            .builder()
            .appComponent(appComponent)
            .build()
    }
}