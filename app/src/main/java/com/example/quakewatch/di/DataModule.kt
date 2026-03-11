package com.example.quakewatch.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.example.quakewatch.data.DefaultQuakeWatchRepository
import com.example.quakewatch.data.source.local.EarthquakeLocalDataSource
import com.example.quakewatch.data.source.local.LocalDataSource
import com.example.quakewatch.data.source.local.datastore.PreferenceDataSource
import com.example.quakewatch.data.source.local.datastore.QuakeWatchPreferencesDataSource
import com.example.quakewatch.data.source.local.datastore.UserPreference
import com.example.quakewatch.data.source.local.datastore.UserPreferenceSerializer
import com.example.quakewatch.data.source.local.room.QuakeWatchDatabase
import com.example.quakewatch.data.source.network.EarthquakeNetworkDataSource
import com.example.quakewatch.data.source.network.NetworkDataSource
import com.example.quakewatch.data.source.network.QuakeWatchService
import com.example.quakewatch.domain.repository.QuakeWatchRepository
import com.example.quakewatch.domain.usecase.GetEarthquakeUseCase
import com.example.quakewatch.domain.usecase.QuakeWatchUseCases
import com.example.quakewatch.domain.usecase.GetSortedEarthquakesUseCase
import com.example.quakewatch.domain.usecase.GetUserPreferenceUseCase
import com.example.quakewatch.domain.usecase.RefreshEarthquakeUseCase
import com.example.quakewatch.domain.usecase.UpdateSortTypePreferenceUseCase
import com.example.quakewatch.domain.usecase.UserPreferenceUseCases
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Binds
    @Singleton
    abstract fun bindPreferenceDataSource(
        quakeWatchPreferencesDataSource: QuakeWatchPreferencesDataSource
    ): PreferenceDataSource

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

    @Provides
    @Singleton
    fun provideUserPreferenceSerializer(): UserPreferenceSerializer = UserPreferenceSerializer()

    @Provides
    @Singleton
    fun provideProtoDataStore(
        @ApplicationContext context: Context,
        userPreferenceSerializer: UserPreferenceSerializer
    ): DataStore<UserPreference> {
        return DataStoreFactory.create(
            serializer = userPreferenceSerializer,
            produceFile = {
                context.dataStoreFile("user_preference.json")
            }
        )
    }

    @Provides
    @Singleton
    fun provideQuakeWatchUseCases(repository: QuakeWatchRepository): QuakeWatchUseCases {
        return QuakeWatchUseCases(
            refreshEarthquake = RefreshEarthquakeUseCase(repository),
            getSortedEarthquakes = GetSortedEarthquakesUseCase(repository),
            getEarthquake = GetEarthquakeUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideUserPreferenceUseCases(repository: QuakeWatchRepository): UserPreferenceUseCases {
        return UserPreferenceUseCases(
            getUserPreference = GetUserPreferenceUseCase(repository),
            updateSortTypePreference = UpdateSortTypePreferenceUseCase(repository)
        )
    }
//
//    @Provides
//    @Singleton
//    fun provideEarthquakeUseCase(repository: QuakeWatchRepository): GetEarthquakeUseCase {
//        return GetEarthquakeUseCase(
//            repository
//        )
//    }

}