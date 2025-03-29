package com.ad.restaurant.domain

import com.ad.restaurant.data.RestaurantsRepo

/**
 * Use case responsible for retrieving a list of restaurants sorted alphabetically by their titles.
 *
 * This class encapsulates the logic for fetching restaurants from the local repository and
 * sorting them in ascending order based on their `title` property.
 */
class GetSortedRestaurantsUseCase {

   private val restaurantsRepo = RestaurantsRepo()

   suspend operator fun invoke() =
      restaurantsRepo.getLocalRestaurants()
         .sortedBy { it.title }
}