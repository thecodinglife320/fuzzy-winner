package com.ad.restaurant.domain

import com.ad.restaurant.data.RestaurantsRepo

class ToggleRestaurantUseCase {
   private val repository = RestaurantsRepo()

   private val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase()

   suspend operator fun invoke(
      id: Int,
      oldValue: Boolean,
   ) =
      let {
         val newFav = oldValue.not()
         repository.toggleFavoriteRestaurant(id, newFav)
         getSortedRestaurantsUseCase()
      }
}