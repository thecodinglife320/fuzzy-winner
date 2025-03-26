package com.ad.restaurant.restaurant

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad.restaurant.model.Restaurant
import com.ad.restaurant.network.RestaurantsApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantViewModel(
   stateHandle: SavedStateHandle,
) : ViewModel() {

   private var restApi: RestaurantsApi

   var uiState: Restaurant? by mutableStateOf(
      null
   )

   private val coroutineExceptionHandler =
      CoroutineExceptionHandler { _, ex ->
         ex.printStackTrace()
      }

   init {
      val retrofit = Retrofit.Builder()
         .addConverterFactory(GsonConverterFactory.create())
         .baseUrl("https://ff11-a857c-default-rtdb.asia-southeast1.firebasedatabase.app")
         .build()

      restApi = retrofit.create(RestaurantsApi::class.java)

      val id = stateHandle.get<Int>("restaurant_id") ?: 0

      getRestaurant(id)
   }

   private fun getRestaurant(id: Int) {
      viewModelScope.launch(coroutineExceptionHandler) {
         val restaurant = getRemoteRestaurant(id)
         uiState = restaurant
      }
   }

   private suspend fun getRemoteRestaurant(id: Int) =
      withContext(Dispatchers.IO) {
         val responseMap = restApi.getRestaurant(id)
         responseMap.values.first()
      }
}