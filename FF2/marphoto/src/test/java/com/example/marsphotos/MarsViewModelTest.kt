package com.example.marsphotos

import com.example.marsphotos.fake.FakeDataSource
import com.example.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.example.marsphotos.rules.TestDispatcherRule
import com.example.marsphotos.ui.stateholder.MarsUiState
import com.example.marsphotos.ui.stateholder.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {

   @get:Rule
   val testDispatcher = TestDispatcherRule()

   @Test
   fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
      runTest {
         val marsViewModel = MarsViewModel(
            marsPhotosRepository = FakeNetworkMarsPhotosRepository()
         )
         assertEquals(
            MarsUiState.Success(FakeDataSource.photosList),
            marsViewModel.marsUiState
         )
      }
}