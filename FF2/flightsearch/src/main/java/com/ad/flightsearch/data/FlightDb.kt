package com.ad.flightsearch.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ad.flightsearch.data.dao.AirportDao
import com.ad.flightsearch.data.dao.FavouriteDao
import com.ad.flightsearch.data.model.Airport
import com.ad.flightsearch.data.model.Favourite

@Database(entities = [Airport::class, Favourite::class], version = 1, exportSchema = false)
abstract class FlightDb : RoomDatabase() {

   abstract fun airportDao(): AirportDao
   abstract fun favouriteDao(): FavouriteDao

}