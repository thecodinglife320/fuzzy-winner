package com.ad.restaurant.ui.restaurants

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad.restaurant.RestaurantsApplication
import com.ad.restaurant.data.local.RestaurantsDb
import com.ad.restaurant.data.model.PartialRestaurant
import com.ad.restaurant.data.model.Restaurant
import com.ad.restaurant.data.network.RestaurantsApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException

class RestaurantsViewModel() : ViewModel(
) {

   var uiState by mutableStateOf(
      emptyList<Restaurant>()
   )

   private var restApi: RestaurantsApi
   private var restaurantsDao = RestaurantsDb
      .getDaoInstance(
         RestaurantsApplication.getAppContext()
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

      viewModelScope.launch(coroutineExceptionHandler) {
         uiState = getSSOTRestaurants()
      }
   }

   private suspend fun getSSOTRestaurants() =
      withContext(Dispatchers.IO) {
         try {

            //refresh cache
            val remoteRestaurants = restApi
               .getRestaurants()

            //du lieu yeu thich
            val favoriteRestaurants = restaurantsDao.getAllFavorite()

            //cache
            restaurantsDao.addAll(remoteRestaurants)

            //update du lieu yeu thich
            restaurantsDao.updateAll(
               favoriteRestaurants.map {
                  PartialRestaurant(it, true)
               }
            )

         } catch (e: Exception) {
            when (e) {
               is UnknownHostException,
               is ConnectException,
               is HttpException,
                  -> {
                  if (restaurantsDao.getAll().isEmpty())
                     throw Exception("Something went wrong.")
               }

               else -> throw e
            }
         }

         restaurantsDao.getAll()
      }

   fun toggleFavoriteRestaurant(
      id: Int,
      oldValue: Boolean,
   ) {
      viewModelScope.launch(Dispatchers.IO) {
         restaurantsDao.update(
            PartialRestaurant(
               id = id,
               isFavorite = !oldValue
            )
         )
         val restaurants = restaurantsDao.getAll()
         withContext(Dispatchers.Main) {
            uiState = restaurants
         }
      }
   }
}


