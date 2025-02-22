package com.ad.amphibians.data

import com.ad.amphibians.fake.FakeAmphibiansApiService
import com.ad.amphibians.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkAmphibiansRepositoryTest {
   @Test
   fun networkAmphibiansRepository_getAmphibians_verifyPhotoList() =
      runTest {
         val repository = NetworkAmphibiansRepository(
            amphibiansApiService = FakeAmphibiansApiService()
         )
         assertEquals(FakeDataSource.amphibiansList, repository.getAmphibians())
      }
}