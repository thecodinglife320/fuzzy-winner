package com.ad.restaurant.data

import com.ad.restaurant.RestaurantsApplication
import com.ad.restaurant.data.local.RestaurantsDb
import com.ad.restaurant.data.model.PartialRestaurant
import com.ad.restaurant.data.network.RestaurantsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException

class RestaurantsRepo {

   private var restApi = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl("https://ff11-a857c-default-rtdb.asia-southeast1.firebasedatabase.app")
      .build()
      .create(RestaurantsApi::class.java)

   private var restaurantsDao = RestaurantsDb
      .getDaoInstance(
         RestaurantsApplication.getAppContext()
      )

   suspend fun getSSOTRestaurants() =
      withContext(Dispatchers.IO) {
         try {

            //refresh cache
            val remoteRestaurants = restApi
               .getRestaurants()

            //du lieu yeu thich
            val favoriteRestaurants = restaurantsDao.getAllFavorite()

            //cache
            restaurantsDao.cacheRestaurants(remoteRestaurants)

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

   suspend fun toggleFavoriteRestaurant(
      id: Int,
      oldValue: Boolean,
   ) = withContext(Dispatchers.IO) {
      restaurantsDao.update(
         PartialRestaurant(id = id, isFavorite = !oldValue)
      )
      restaurantsDao.getAll()
   }

   suspend fun getRestaurant(id: Int) =
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