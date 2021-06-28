package com.funcrib.cachingstrategy.data.remote

import com.funcrib.cachingstrategy.data.domain.Restaurant
import retrofit2.http.GET

interface ApiService {

    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurant(): List<Restaurant>
}