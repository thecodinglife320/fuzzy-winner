package com.ad.flightsearch.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite")
data class Favourite(
   @PrimaryKey(autoGenerate = true)
   val id: Int = 0,
   val departure_code: String = "",
   val destination_code: String = "",
)