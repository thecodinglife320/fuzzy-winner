package com.ad.restaurant.data.model

import androidx.room.ColumnInfo

data class PartialLocalRestaurant(

   @ColumnInfo(name = "r_id")
   val id: Int,

   @ColumnInfo(name = "is_favourite")
   val isFavorite: Boolean,
)