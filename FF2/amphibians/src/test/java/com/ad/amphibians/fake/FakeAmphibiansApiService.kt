package com.ad.amphibians.fake

import com.ad.amphibians.model.Amphibians
import com.ad.amphibians.network.AmphibiansApiService

class FakeAmphibiansApiService : AmphibiansApiService {
   override suspend fun getAmphibians(): List<Amphibians> {
      return FakeDataSource.amphibiansList
   }
}