package com.ad.restaurant.network

import com.ad.restaurant.model.Restaurant
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantsApi {

   @GET("restaurants.json")
   suspend fun getRestaurants(): List<Restaurant>

   @GET("restaurants.json?orderBy=\"r_id\" ")
   suspend fun getRestaurant(
      @Query("equalTo") id: Int,
   ): Map<String, Restaurant>
}