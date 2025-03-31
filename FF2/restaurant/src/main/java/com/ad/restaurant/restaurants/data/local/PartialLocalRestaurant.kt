package com.ad.restaurant.restaurants.data.local

import androidx.room.ColumnInfo

data class PartialLocalRestaurant(

   @ColumnInfo(name = "r_id")
   val id: Int,

   @ColumnInfo(name = "is_favourite")
   val isFavorite: Boolean,
)