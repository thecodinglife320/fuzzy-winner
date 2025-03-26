package com.ad.flightsearch.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.ad.flightsearch.data.model.Airport
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {

   @Query("SELECT * FROM airport WHERE iata_code LIKE :query OR name LIKE :query ORDER BY passengers DESC")
   fun searchAirports(query: String): Flow<List<Airport>>

   @Query("SELECT name FROM airport WHERE id = :id")
   fun getName(id: Int): String
}