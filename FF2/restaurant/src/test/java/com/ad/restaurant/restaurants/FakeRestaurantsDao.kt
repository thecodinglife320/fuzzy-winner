package com.ad.restaurant.restaurants

import com.ad.restaurant.restaurants.data.local.LocalRestaurant
import com.ad.restaurant.restaurants.data.local.PartialLocalRestaurant
import com.ad.restaurant.restaurants.data.local.RestaurantsDao
import kotlinx.coroutines.delay

class FakeRestaurantsDao : RestaurantsDao {

   private var restaurants = HashMap<Int, LocalRestaurant>()

   override suspend fun getAll() =
      let {
         delay(1000)
         restaurants.values.toList()
      }

   override suspend fun cacheRestaurants(restaurants: List<LocalRestaurant>) {
   }

   override suspend fun update(partialRestaurant: PartialLocalRestaurant) {
      delay(1000)
      updateRestaurant(partialRestaurant)
   }

   private fun updateRestaurant(partialLocalRestaurant: PartialLocalRestaurant) {
      val restaurant = restaurants[partialLocalRestaurant.id]
      restaurant?.let {
         restaurants[partialLocalRestaurant.id] = restaurant.copy(
            isFavourite = partialLocalRestaurant.isFavorite
         )
      }
   }

   override suspend fun updateAll(partialRestaurants: List<PartialLocalRestaurant>) {
      delay(1000)
      partialRestaurants.forEach {
         update(it)
      }
   }

   override suspend fun getAllFavorite(): List<Int> {
      return restaurants.values.toList().filter { it.isFavourite }.map { it.id }
   }

   override suspend fun getById(id: Int): LocalRestaurant? {
      return restaurants[id]
   }
}