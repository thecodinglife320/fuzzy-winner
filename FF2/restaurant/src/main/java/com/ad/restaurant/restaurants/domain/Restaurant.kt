package com.ad.restaurant.restaurants.domain

import com.ad.restaurant.restaurants.data.remote.RemoteRestaurant

data class Restaurant(

   val id: Int,
   val title: String,
   val description: String,
   val isFavourite: Boolean = false,
)

object DummyRestaurants {
   val restaurant = Restaurant(
      id = 1,
      title = "anh duong",
      description = "ngon, bổ, rẻ",
      isFavourite = false
   )

   val restaurants = listOf(
      Restaurant(
         id = 2,
         title = "nha hang doraemon",
         description = "abc",
         isFavourite = false
      ),
      restaurant
   )

   fun getRemoteRestaurants() = restaurants.map {
      RemoteRestaurant(
         id = it.id,
         title = it.title,
         description = it.description
      )
   }
}
