package com.ad.restaurant.ui.details

import com.ad.restaurant.data.model.Restaurant

data class RestaurantScreenState(
   val restaurant: Restaurant? = null,
   val isLoading: Boolean,
   val error: String? = null,
)