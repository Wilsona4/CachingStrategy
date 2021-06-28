package com.funcrib.cachingstrategy.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants_table")
data class RestaurantEntity(
    val address: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val logo: String,
    val name: String,
    val phone_number: String,
    val type: String,
)
