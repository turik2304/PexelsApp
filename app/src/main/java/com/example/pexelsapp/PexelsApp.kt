package com.example.pexelsapp

import android.app.Application
import com.example.pexelsapp.di.components.AppComponent
import com.example.pexelsapp.di.components.DaggerAppComponent
import com.example.pexelsapp.di.modules.AppModule

class PexelsApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        appComponent.inject(this)
    }
}