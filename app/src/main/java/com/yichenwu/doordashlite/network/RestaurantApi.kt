package com.yichenwu.doordashlite.network

import com.yichenwu.doordashlite.model.data.Restaurant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantApi {
    @GET("v2/restaurant")
    fun getRestaurants(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Observable<List<Restaurant>>
}
