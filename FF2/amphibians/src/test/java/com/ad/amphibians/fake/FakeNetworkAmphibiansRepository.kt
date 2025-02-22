package com.ad.amphibians.fake

import com.ad.amphibians.data.AmphibiansRepository
import com.ad.amphibians.model.Amphibians

class FakeNetworkAmphibiansRepository : AmphibiansRepository {
   override suspend fun getAmphibians(): List<Amphibians> {
      return FakeDataSource.amphibiansList
   }

}