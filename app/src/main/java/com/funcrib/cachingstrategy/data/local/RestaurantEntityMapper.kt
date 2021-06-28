package com.funcrib.cachingstrategy.data.local

import com.funcrib.cachingstrategy.data.domain.Restaurant
import com.funcrib.cachingstrategy.util.DomainMapper
import javax.inject.Inject

class RestaurantEntityMapper @Inject constructor() : DomainMapper<RestaurantEntity, Restaurant> {
    override fun mapToDomainModel(model: RestaurantEntity): Restaurant {
        return Restaurant(
            address = model.address,
            id = model.id,
            logo = model.logo,
            name = model.name,
            phone_number = model.phone_number,
            type = model.type
        )
    }

    override fun mapFromDomainModel(domainModel: Restaurant): RestaurantEntity {
        return RestaurantEntity(
            address = domainModel.address,
            id = domainModel.id,
            logo = domainModel.logo,
            name = domainModel.name,
            phone_number = domainModel.phone_number,
            type = domainModel.type
        )
    }

    fun mapToDomainModelList(model: List<RestaurantEntity>): List<Restaurant> {
        return model.map { mapToDomainModel(it) }
    }

    fun mapFromDomainModelList(domainModel: List<Restaurant>): List<RestaurantEntity> {
        return domainModel.map { mapFromDomainModel(it) }
    }

}