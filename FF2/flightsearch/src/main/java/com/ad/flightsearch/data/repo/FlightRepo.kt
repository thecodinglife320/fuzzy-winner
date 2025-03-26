package com.ad.flightsearch.data.repo

import com.ad.flightsearch.data.dao.AirportDao
import com.ad.flightsearch.data.model.Airport
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlightRepo @Inject constructor(
   private val airportDao: AirportDao,
) {

   lateinit var airPortFlow: Flow<List<Airport>>

   init {
      searchAirports("%OP%")
   }

   fun searchAirports(query: String) {
      airPortFlow = airportDao.searchAirports(query)
   }

   fun getName(id: Int) = airportDao.getName(id)
}
