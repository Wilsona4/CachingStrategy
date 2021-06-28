package com.funcrib.cachingstrategy.di

import com.funcrib.cachingstrategy.data.local.RestaurantDatabase
import com.funcrib.cachingstrategy.data.local.RestaurantEntityMapper
import com.funcrib.cachingstrategy.data.remote.ApiService
import com.funcrib.cachingstrategy.repository.Repository
import com.funcrib.cachingstrategy.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService,
        database: RestaurantDatabase,
        restaurantEntityMapper: RestaurantEntityMapper
    ): Repository {
        return RepositoryImpl(apiService, database, restaurantEntityMapper)
    }
}