package com.ad.amphibians.data

import com.ad.amphibians.model.Amphibians
import com.ad.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
   suspend fun getAmphibians(): List<Amphibians>
}

class NetworkAmphibiansRepository(
   private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {

   override suspend fun getAmphibians(): List<Amphibians> {
      return amphibiansApiService.getAmphibians()
   }

}