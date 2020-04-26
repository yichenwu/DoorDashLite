package com.yichenwu.doordashlite.repository

import com.yichenwu.doordashlite.model.data.Restaurant
import com.yichenwu.doordashlite.network.RestaurantApi
import com.yichenwu.doordashlite.network.RetrofitProvider
import io.reactivex.Observable

class RestaurantRepository {
    private val restaurantApiClient = RetrofitProvider.get().create(RestaurantApi::class.java)

    fun getRestaurants(
        latitude: Double = DEFAULT_LATITUDE,
        longitude: Double = DEFAULT_LONGITUDE,
        offset: Int = DEFAULT_OFFSET,
        limit: Int = DEFAULT_LIMIT
    ) : Observable<List<Restaurant>> = restaurantApiClient.getRestaurants(latitude, longitude, offset, limit)

    companion object {
        private const val DEFAULT_LATITUDE = 37.422740
        private const val DEFAULT_LONGITUDE = -122.139956
        private const val DEFAULT_OFFSET = 0
        private const val DEFAULT_LIMIT = 50
    }
}
