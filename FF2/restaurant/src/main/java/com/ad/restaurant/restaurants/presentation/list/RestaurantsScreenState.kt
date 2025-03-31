package com.ad.restaurant.restaurants.presentation.list

import com.ad.restaurant.restaurants.domain.Restaurant

data class RestaurantsScreenState(
   val restaurants: List<Restaurant>,
   val isLoading: Boolean,
   val error: String? = null,
)
