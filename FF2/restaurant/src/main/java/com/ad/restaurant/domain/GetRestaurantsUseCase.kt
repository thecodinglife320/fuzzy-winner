package com.ad.restaurant.domain

import com.ad.restaurant.data.RestaurantsRepo

class GetRestaurantsUseCase {

   private val restaurantsRepo = RestaurantsRepo()

   private val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase()

   suspend operator fun invoke() =
      let {
         restaurantsRepo.cacheRestaurants()
         getSortedRestaurantsUseCase()
      }
}