package com.ad.restaurant.domain

import com.ad.restaurant.data.RestaurantsRepo

/**
 * Use case responsible for retrieving and processing a list of restaurants.
 *
 * This class cache remote data and applies sorting logic to the retrieved data. It acts as a high-level
 * operation that encapsulates the steps involved in getting a ready-to-use
 * list of restaurants.
 */
class GetRestaurantsUseCase {

   private val restaurantsRepo = RestaurantsRepo()

   private val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase()

   suspend operator fun invoke() =
      let {
         restaurantsRepo.cacheRestaurants()
         getSortedRestaurantsUseCase()
      }
}