package com.funcrib.cachingstrategy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.funcrib.cachingstrategy.data.domain.Restaurant

@Database(entities = [RestaurantEntity::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    companion object {
        var DATABASE_NAME: String = "restaurant_db"
    }
}