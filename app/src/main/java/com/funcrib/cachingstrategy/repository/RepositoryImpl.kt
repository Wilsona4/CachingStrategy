package com.funcrib.cachingstrategy.repository

import androidx.room.withTransaction
import com.funcrib.cachingstrategy.data.domain.Restaurant
import com.funcrib.cachingstrategy.data.local.RestaurantDatabase
import com.funcrib.cachingstrategy.data.local.RestaurantEntityMapper
import com.funcrib.cachingstrategy.data.remote.ApiService
import com.funcrib.cachingstrategy.util.Resource
import com.funcrib.cachingstrategy.util.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: RestaurantDatabase,
    private val restaurantEntityMapper: RestaurantEntityMapper,
) : Repository {

    override fun getRestaurant(): Flow<Resource<List<Restaurant>>> = networkBoundResource(
        fetchFromLocal = {
            database.restaurantDao().readRestaurants().map {
                restaurantEntityMapper.mapToDomainModelList(it)
            }
        },
        shouldFetchFromRemote = {
            true
        },
        fetchFromRemote = {
            delay(2000)
            apiService.getRestaurant()
        },
        saveToLocalDB = {
            database.withTransaction {
                database.restaurantDao().deleteAllRestaurants()
                database.restaurantDao()
                    .addRestaurants(restaurantEntityMapper.mapFromDomainModelList(it))
            }
        }
    )
}
