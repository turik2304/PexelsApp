package com.example.pexelsapp.di.components

import com.example.pexelsapp.di.modules.SearchModule
import com.example.pexelsapp.di.scopes.AppScope
import com.example.pexelsapp.di.scopes.SearchScope
import com.example.pexelsapp.presentation.SearchFragment
import dagger.Component

@SearchScope
@Component(
    dependencies = [AppComponent::class],
    modules = [SearchModule::class]
)
interface SearchComponent {
    fun inject(searchFragment: SearchFragment)
}