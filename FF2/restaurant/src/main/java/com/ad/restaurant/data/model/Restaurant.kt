package com.ad.restaurant.data.model

data class Restaurant(

   val id: Int,
   val title: String,
   val description: String,
   val isFavourite: Boolean = false,
)