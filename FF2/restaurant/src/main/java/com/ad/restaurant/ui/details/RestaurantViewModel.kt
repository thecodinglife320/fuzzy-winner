package com.ad.restaurant.ui.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad.restaurant.domain.GetRestaurantUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class RestaurantViewModel(
   stateHandle: SavedStateHandle,
) : ViewModel() {

   //prepare data
   private val _uiState = mutableStateOf(
      RestaurantScreenState(
         restaurant = null,
         isLoading = true,
      )
   )

   //expose data
   val uiState: State<RestaurantScreenState>
      get() = _uiState

   private val getRestaurantUseCase = GetRestaurantUseCase()

   private val coroutineExceptionHandler =
      CoroutineExceptionHandler { _, ex ->
         ex.printStackTrace()
         _uiState.value = _uiState.value.copy(
            isLoading = false,
            error = ex.message
         )
      }

   init {
      val id = stateHandle.get<Int>("restaurant_id") ?: 0

      viewModelScope.launch(coroutineExceptionHandler) {
         _uiState.value = _uiState.value.copy(
            restaurant = getRestaurantUseCase(id),
            isLoading = false,
         )
      }
   }
}