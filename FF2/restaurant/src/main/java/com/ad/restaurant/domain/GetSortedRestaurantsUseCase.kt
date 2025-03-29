package com.ad.restaurant.domain

import com.ad.restaurant.data.RestaurantsRepo

class GetSortedRestaurantsUseCase {

   private val restaurantsRepo = RestaurantsRepo()

   suspend operator fun invoke() =
      restaurantsRepo.getLocalRestaurants()
         .sortedBy { it.title }
}