package com.ad.restaurant.domain

import com.ad.restaurant.data.RestaurantsRepo

/**
 * Use case responsible for toggling the favorite status of a restaurant.
 *
 * This class encapsulates the logic for marking a restaurant as a favorite or unfavorite.
 * It interacts with the [RestaurantsRepo] to update the restaurant's favorite status and
 * then retrieves the updated list of sorted restaurants using [GetSortedRestaurantsUseCase].
 */
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