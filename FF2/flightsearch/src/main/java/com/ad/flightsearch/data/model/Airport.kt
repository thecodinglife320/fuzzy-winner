package com.ad.flightsearch.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
   @PrimaryKey(autoGenerate = true)
   val id: Int = 0,
   val iata_code: String = "",
   val name: String = "",
   val passengers: Int = 0,
)