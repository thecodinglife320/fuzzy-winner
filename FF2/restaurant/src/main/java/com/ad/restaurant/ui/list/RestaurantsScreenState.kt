package com.ad.restaurant.ui.list

import com.ad.restaurant.data.model.Restaurant

data class RestaurantsScreenState(
   val restaurants: List<Restaurant>,
   val isLoading: Boolean,
   val error: String? = null,
)
