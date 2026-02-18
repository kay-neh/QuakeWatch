package com.example.quakewatch.di

import com.example.quakewatch.data.QuakeWatchRepositoryImpl
import com.example.quakewatch.domain.QuakeWatchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindQuakeWatchRepository(
        quakeWatchRepositoryImpl: QuakeWatchRepositoryImpl
    ): QuakeWatchRepository

}