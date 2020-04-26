package com.yichenwu.doordashlite.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yichenwu.doordashlite.model.data.Restaurant

class RestaurantListAdapter :
    RecyclerView.Adapter<RestaurantListAdapter.RestaurantCardViewHolder>() {

    var restaurants = mutableListOf<Restaurant>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class RestaurantCardViewHolder(val restaurantCardView: RestaurantCardView) :
        RecyclerView.ViewHolder(restaurantCardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantCardViewHolder =
        RestaurantCardViewHolder(RestaurantCardView(parent.context))

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: RestaurantCardViewHolder, position: Int) {
        val restaurantCardView = holder.restaurantCardView
        val restaurant = restaurants[position]

        restaurantCardView.showImage(restaurant.imageUrl)
        restaurantCardView.showName(restaurant.name)
        if (restaurant.description == null) {
            restaurantCardView.hideDescription()
        } else {
            restaurantCardView.showDescription(restaurant.description)
        }
        if (restaurant.status == null) {
            restaurantCardView.hideStatus()
        } else {
            restaurantCardView.showStatus(restaurant.status)
        }
    }

    /*
        append more restaurant to the end of the list, can be used for pagination later
     */
    fun addRestaurants(newRestaurants: List<Restaurant>) {
        restaurants.addAll(newRestaurants)
        notifyDataSetChanged()
    }
}
