package com.example.pexelsapp.di.modules

import android.app.Application
import android.content.Context
import com.example.pexelsapp.di.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    @AppScope
    fun provideContext(): Context = application
}
