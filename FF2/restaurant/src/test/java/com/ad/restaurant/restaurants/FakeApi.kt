package com.ad.restaurant.restaurants

import com.ad.restaurant.restaurants.data.remote.RemoteRestaurant
import com.ad.restaurant.restaurants.data.remote.RestaurantsApi
import com.ad.restaurant.restaurants.domain.DummyRestaurants
import kotlinx.coroutines.delay

class FakeApi : RestaurantsApi {
   override suspend fun getRestaurants(): List<RemoteRestaurant> {
      delay(1000)
      return DummyRestaurants.getRemoteRestaurants()
   }

   override suspend fun getRestaurant(id: Int): Map<String, RemoteRestaurant> {
      TODO("Not yet implemented")
   }
}