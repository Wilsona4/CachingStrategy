package com.funcrib.cachingstrategy.repository

import com.funcrib.cachingstrategy.data.domain.Restaurant
import com.funcrib.cachingstrategy.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getRestaurant(): Flow<Resource<List<Restaurant>>>
}