package com.example.quakewatch.di

import android.app.Application
import androidx.room.Room
import com.example.quakewatch.data.DefaultQuakeWatchRepository
import com.example.quakewatch.data.source.local.EarthquakeLocalDataSource
import com.example.quakewatch.data.source.local.LocalDataSource
import com.example.quakewatch.data.source.local.QuakeWatchDatabase
import com.example.quakewatch.data.source.network.EarthquakeNetworkDataSource
import com.example.quakewatch.data.source.network.NetworkDataSource
import com.example.quakewatch.data.source.network.QuakeWatchService
import com.example.quakewatch.domain.QuakeWatchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindQuakeWatchRepository(
        defaultQuakeWatchRepository: DefaultQuakeWatchRepository
    ): QuakeWatchRepository

    @Binds
    @Singleton
    abstract fun bindNetworkDataSource(
        earthquakeNetworkDataSource: EarthquakeNetworkDataSource
    ): NetworkDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        earthquakeLocalDataSource: EarthquakeLocalDataSource
    ): LocalDataSource

}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuakeWatchService(): QuakeWatchService {
        return Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuakeWatchService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuakeWatchDatabase(app: Application): QuakeWatchDatabase {
        return Room.databaseBuilder(
            app,
            QuakeWatchDatabase::class.java,
            "quake_watch_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideEarthquakeDao(db: QuakeWatchDatabase) = db.dao


}