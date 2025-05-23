package com.ad.restaurant.restaurants.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantsApi {

   @GET("restaurants.json")
   suspend fun getRestaurants(): List<RemoteRestaurant>

   @GET("restaurants.json?orderBy=\"r_id\" ")
   suspend fun getRestaurant(
      @Query("equalTo") id: Int,
   ): Map<String, RemoteRestaurant>
}