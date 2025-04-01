package com.ad.restaurant.restaurants.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad.restaurant.restaurants.data.di.MainDispatcher
import com.ad.restaurant.restaurants.domain.GetRestaurantsUseCase
import com.ad.restaurant.restaurants.domain.ToggleRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
   private val toggleRestaurantUseCase: ToggleRestaurantUseCase,
   private val getRestaurantsUseCase: GetRestaurantsUseCase,
   @MainDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

   private val _uiState = mutableStateOf(
      RestaurantsScreenState(
         restaurants = listOf(),
         isLoading = true
      )
   )

   val uiState: State<RestaurantsScreenState> get() = _uiState

   private val coroutineExceptionHandler =
      CoroutineExceptionHandler { _, ex ->
         ex.printStackTrace()
         _uiState.value = _uiState.value.copy(
            isLoading = false,
            error = ex.message
         )
      }

   init {
      viewModelScope.launch(coroutineExceptionHandler + dispatcher) {
         _uiState.value = _uiState.value.copy(
            restaurants = getRestaurantsUseCase(),
            isLoading = false
         )
      }
   }

   fun toggleFavoriteRestaurant(
      id: Int,
      oldValue: Boolean,
   ) {
      viewModelScope.launch(dispatcher) {
         _uiState.value = uiState.value.copy(
            restaurants = toggleRestaurantUseCase(
               id = id,
               oldValue = oldValue
            )
         )
      }
   }
}


