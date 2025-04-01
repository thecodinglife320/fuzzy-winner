package com.ad.restaurant.restaurants.presentation.list

import com.ad.restaurant.restaurants.FakeApi
import com.ad.restaurant.restaurants.FakeRestaurantsDao
import com.ad.restaurant.restaurants.data.RestaurantsRepo
import com.ad.restaurant.restaurants.domain.GetRestaurantsUseCase
import com.ad.restaurant.restaurants.domain.GetSortedRestaurantsUseCase
import com.ad.restaurant.restaurants.domain.ToggleRestaurantUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class RestaurantsViewModelTest {

   private val dispatcher = StandardTestDispatcher()
   private val scope = TestScope(dispatcher)


   @Test
   fun initialState_isProduced() = scope.runTest {
      val viewModel = getViewModel()
      val initialState = viewModel.uiState.value
      assertEquals(
         initialState, RestaurantsScreenState(
            restaurants = emptyList(),
            isLoading = true,
            error = null
         )
      )
   }

   @Test
   fun stateWithContent_isProduced() = scope.runTest {
      val testVM = getViewModel()
      advanceUntilIdle()
      val currentState = testVM.uiState.value
      println(currentState)
   }

   private fun getViewModel(): RestaurantsViewModel {
      val restaurantsRepo = RestaurantsRepo(
         restApi = FakeApi(),
         restaurantsDao = FakeRestaurantsDao(),
         dispatcher = dispatcher
      )

      val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase(restaurantsRepo)

      val getRestaurantsUseCase =
         GetRestaurantsUseCase(restaurantsRepo, getSortedRestaurantsUseCase)

      val toggleRestaurantUseCase = ToggleRestaurantUseCase(
         repository = restaurantsRepo,
         getSortedRestaurantsUseCase = getSortedRestaurantsUseCase
      )

      return RestaurantsViewModel(
         toggleRestaurantUseCase = toggleRestaurantUseCase,
         getRestaurantsUseCase = getRestaurantsUseCase,
         dispatcher
      )

   }
}