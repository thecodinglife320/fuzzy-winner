package com.ad.restaurant.restaurants

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad.restaurant.model.Restaurant
import com.ad.restaurant.network.RestaurantsApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantsViewModel(
   private val stateHandle: SavedStateHandle,
) : ViewModel(
) {

   var uiState by mutableStateOf(
      emptyList<Restaurant>()
   )

   private var restApi: RestaurantsApi

   fun toggleFavourite(restaurant: Restaurant) {
      val restaurants = uiState.toMutableList()
      val index = uiState.indexOf(restaurant)
      restaurants[index] = restaurant.copy(isFavourite = !restaurant.isFavourite)
      storeSelection(restaurants[index])
      uiState = restaurants
   }

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
      getRestaurants()

   }

   private fun storeSelection(restaurant: Restaurant) {

      val savedToggle = stateHandle
         .get<List<Int>>(FAVORITES)
         .orEmpty()
         .toMutableList()

      if (restaurant.isFavourite) savedToggle.add(restaurant.id)
      stateHandle[FAVORITES] = savedToggle
   }

   companion object {
      const val FAVORITES = "favorites"
   }

   private fun List<Restaurant>.restoreSelections(): List<Restaurant> {
      stateHandle.get<List<Int>>(FAVORITES)?.let { savedToggle ->
         val restaurantsMap = this.associateBy { it.id }
         savedToggle.forEach { id ->
            restaurantsMap[id]?.isFavourite = true
         }
         return restaurantsMap.values.toList()
      }
      return this
   }

   private fun getRestaurants() {
      viewModelScope.launch(coroutineExceptionHandler) {
         val restaurants = getRemoteRestaurants()
         uiState = restaurants.restoreSelections()
      }
   }

   private suspend fun getRemoteRestaurants() = restApi.getRestaurants()
}


