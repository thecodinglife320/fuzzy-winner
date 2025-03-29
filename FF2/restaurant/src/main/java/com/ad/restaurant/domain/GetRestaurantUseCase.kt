package com.ad.restaurant.domain

import com.ad.restaurant.data.RestaurantsRepo

/**
 * Use case for retrieving a specific restaurant by its ID.
 *
 * This class encapsulates the logic for fetching a restaurant from the local data source
 * based on the provided restaurant ID. It interacts with the [RestaurantsRepo] to
 * retrieve the restaurant data.
 */
class GetRestaurantUseCase {

   private val restaurantsRepo = RestaurantsRepo()

   suspend operator fun invoke(id: Int) =
      let {
         restaurantsRepo.getLocalRestaurant(id)
      }
}