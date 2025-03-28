package com.ad.restaurant.ui.restaurants

import com.ad.restaurant.data.model.Restaurant

data class RestaurantsScreenState(
   val restaurants: List<Restaurant>,
   val isLoading: Boolean,
   val error: String? = null,
)
