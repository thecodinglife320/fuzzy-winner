package com.ad.restaurant.ui.restaurants

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad.restaurant.data.RestaurantsRepo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantsViewModel() : ViewModel(
) {

   private val _uiState = mutableStateOf(
      RestaurantsScreenState(
         restaurants = listOf(),
         isLoading = true
      )
   )

   val uiState: State<RestaurantsScreenState> get() = _uiState

   private val restaurantsRepo = RestaurantsRepo()

   private val coroutineExceptionHandler =
      CoroutineExceptionHandler { _, ex ->
         ex.printStackTrace()
         _uiState.value = _uiState.value.copy(
            isLoading = false,
            error = ex.message
         )
      }

   init {
      viewModelScope.launch(coroutineExceptionHandler) {
         _uiState.value = _uiState.value.copy(
            restaurants = restaurantsRepo.getSSOTRestaurants(),
            isLoading = false
         )
      }
   }

   fun toggleFavoriteRestaurant(
      id: Int,
      oldValue: Boolean,
   ) {
      viewModelScope.launch(Dispatchers.Main) {
         _uiState.value = uiState.value.copy(
            restaurants = restaurantsRepo.toggleFavoriteRestaurant(id, oldValue)
         )
      }
   }
}


