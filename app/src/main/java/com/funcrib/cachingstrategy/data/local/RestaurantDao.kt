package com.funcrib.cachingstrategy.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    /*Add restaurants to Database*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRestaurants(restaurants: List<RestaurantEntity>)

    /*Get restaurants in the Database*/
    @Query("SELECT * FROM restaurants_table")
    fun readRestaurants(): Flow<List<RestaurantEntity>>

    /*Delete restaurants in the Database*/
    @Query("DELETE FROM restaurants_table")
    suspend fun deleteAllRestaurants()
}