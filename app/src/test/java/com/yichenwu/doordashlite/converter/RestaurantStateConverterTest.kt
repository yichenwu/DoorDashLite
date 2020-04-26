package com.yichenwu.doordashlite.converter

import com.yichenwu.doordashlite.model.data.Restaurant
import com.yichenwu.doordashlite.model.ui.EmptyState
import com.yichenwu.doordashlite.model.ui.SuccessState
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

internal class RestaurantStateConverterTest {
    private val converter = RestaurantStateConverter

    @Test
    fun `convert should convert to SuccessState when restaurant list has something`() {
        // GIVEN
        val restaurant = Restaurant(0, "", "", "", "", 0)
        val restaurants = listOf(restaurant)

        // WHEN
        val state = converter.convert(restaurants)

        // THEN
        state shouldBeInstanceOf SuccessState::class
        val successState = state as SuccessState
        successState.restaurants.size shouldBe 1
        successState.restaurants[0] shouldBe restaurant
    }

    @Test
    fun `convert should convert to EmptyState when restaurant list is empty`() {
        // GIVEN
        val restaurants = emptyList<Restaurant>()

        // WHEN
        val state = converter.convert(restaurants)

        // THEN
        state shouldBeInstanceOf EmptyState::class
    }
}