package com.example.pexelsapp.di.components

import android.app.Application
import com.example.pexelsapp.data.repository.PexelsRepository
import com.example.pexelsapp.di.modules.AppModule
import com.example.pexelsapp.di.modules.OkHttpModule
import com.example.pexelsapp.di.modules.RepositoryModule
import com.example.pexelsapp.di.modules.RetrofitModule
import com.example.pexelsapp.di.scopes.AppScope
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, RepositoryModule::class, RetrofitModule::class, OkHttpModule::class])
interface AppComponent {
    fun inject(app: Application)
    fun repo(): PexelsRepository
}