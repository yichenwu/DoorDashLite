package com.yichenwu.doordashlite.converter

import com.yichenwu.doordashlite.model.data.Restaurant
import com.yichenwu.doordashlite.model.ui.EmptyState
import com.yichenwu.doordashlite.model.ui.State
import com.yichenwu.doordashlite.model.ui.SuccessState

object RestaurantStateConverter {
    // convert the data model to ui(view) model, ready for ui to render
    fun convert(restaurants: List<Restaurant>): State =
        if (restaurants.isEmpty()) EmptyState else SuccessState(restaurants)
}
