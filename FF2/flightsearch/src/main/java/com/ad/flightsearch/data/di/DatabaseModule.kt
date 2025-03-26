package com.ad.flightsearch.data.di

import android.content.Context
import androidx.room.Room
import com.ad.flightsearch.data.FlightDb
import com.ad.flightsearch.data.dao.AirportDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

   @Provides
   @Singleton
   fun provideDatabase(@ApplicationContext context: Context): FlightDb {
      return Room.databaseBuilder(
         context.applicationContext,
         FlightDb::class.java,
         "flight.db"
      )
         .createFromAsset("database/flight_search.db")
         .build()
   }

   @Provides
   fun provideAirportDao(database: FlightDb): AirportDao {
      return database.airportDao()
   }
}
