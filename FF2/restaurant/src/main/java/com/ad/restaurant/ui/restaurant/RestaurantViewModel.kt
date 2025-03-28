package com.ad.restaurant.ui.restaurant

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad.restaurant.RestaurantsApplication
import com.ad.restaurant.data.local.RestaurantsDb
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

class RestaurantViewModel(
   stateHandle: SavedStateHandle,
) : ViewModel() {

   private var restApi: RestaurantsApi
   private var restaurantsDao = RestaurantsDb
      .getDaoInstance(
         RestaurantsApplication.getAppContext()
      )

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

      viewModelScope.launch(coroutineExceptionHandler) {
         uiState = getRestaurant(id)
      }
   }

   private suspend fun getRestaurant(id: Int) =
      withContext(Dispatchers.IO) {
         try {
            val responseMap = restApi.getRestaurant(id)
            return@withContext responseMap.values.first()
         } catch (e: Exception) {
            when (e) {
               is UnknownHostException,
               is ConnectException,
               is HttpException,
                  -> {
                  return@withContext restaurantsDao.getById(id)
                     ?: throw Exception("Something went wrong.")
               }

               else -> throw e
            }
         }
         return@withContext null
      }
}