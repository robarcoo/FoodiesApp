package com.example.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import com.example.data.repository.ProductsRepositoryImpl
import dagger.Component
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object ProductModule {

    @ViewModelScoped
    @Provides
    fun provideRepository() : ProductsRepositoryImpl {
        return ProductsRepositoryImpl()
    }
}
