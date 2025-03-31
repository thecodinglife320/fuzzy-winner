package com.ad.restaurant.restaurants.presentation.details

import com.ad.restaurant.restaurants.domain.Restaurant

data class RestaurantScreenState(
   val restaurant: Restaurant? = null,
   val isLoading: Boolean,
   val error: String? = null,
)