package com.ad.amphibians.ui.stateholder

import com.ad.amphibians.fake.FakeDataSource
import com.ad.amphibians.fake.FakeNetworkAmphibiansRepository
import com.ad.amphibians.rules.TestDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class AmphibiansViewModelTest {

   @get:Rule
   val testDispatcher = TestDispatcherRule()

   @Test
   fun amphibiansViewModel_getAmphibians_verifyMarsUiStateSuccess() =
      runTest {
         val amphibiansViewModel = AmphibiansViewModel(
            amphibiansRepository = FakeNetworkAmphibiansRepository(),
         )
         assertEquals(
            AmphibiansUiState.Success(FakeDataSource.amphibiansList),
            amphibiansViewModel.amphibiansUiState
         )
      }
}