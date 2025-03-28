package com.ad.restaurant.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "restaurants")
data class Restaurant(

   @PrimaryKey
   @SerializedName("r_id")
   @ColumnInfo("r_id")
   val id: Int,

   @SerializedName("r_title")
   @ColumnInfo("r_title")
   val title: String,

   @SerializedName("r_description")
   @ColumnInfo("r_description")
   val description: String,

   @ColumnInfo(name = "is_favourite")
   val isFavourite: Boolean = false,
)