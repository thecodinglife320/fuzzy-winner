package com.ad.restaurant.restaurants.domain

data class Restaurant(

   val id: Int,
   val title: String,
   val description: String,
   val isFavourite: Boolean = false,
)

object RestaurantsSample {
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
}
