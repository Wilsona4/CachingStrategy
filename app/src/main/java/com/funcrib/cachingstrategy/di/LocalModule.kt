package com.funcrib.cachingstrategy.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.funcrib.cachingstrategy.data.local.RestaurantDao
import com.funcrib.cachingstrategy.data.local.RestaurantDatabase
import com.funcrib.cachingstrategy.data.local.RestaurantDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun providesDataBase(@ApplicationContext context: Context): RestaurantDatabase {
        return databaseBuilder(context, RestaurantDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesClientDAO(restaurantDatabase: RestaurantDatabase): RestaurantDao {
        return restaurantDatabase.restaurantDao()
    }

}
