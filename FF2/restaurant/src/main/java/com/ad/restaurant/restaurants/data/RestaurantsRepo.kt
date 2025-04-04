package com.ad.restaurant.restaurants.data

import com.ad.restaurant.restaurants.data.di.IoDispatcher
import com.ad.restaurant.restaurants.data.local.LocalRestaurant
import com.ad.restaurant.restaurants.data.local.PartialLocalRestaurant
import com.ad.restaurant.restaurants.data.local.RestaurantsDao
import com.ad.restaurant.restaurants.data.remote.RestaurantsApi
import com.ad.restaurant.restaurants.domain.Restaurant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantsRepo @Inject constructor(
   private var restApi: RestaurantsApi,
   private var restaurantsDao: RestaurantsDao,
   @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {
   /**
    * Caches restaurant data retrieved from a remote API into a local database.
    *
    * This function performs the following actions within a background thread (Dispatchers.IO):
    * 1. **Retrieves Remote Data:** Fetches the latest restaurant data from the remote API using `restApi.getRestaurants()`.
    * 2. **Retrieves Favorite Data:** Retrieves the list of currently favorited restaurants from the local database using `restaurantsDao.getAllFavorite()`.
    * 3. **Caches Remote Data:** Caches the newly retrieved remote restaurant data into the local database using `restaurantsDao.cacheRestaurants()`.
    *    - The remote data is transformed into a list of `LocalRestaurant` objects. Initially, all restaurants are marked as not being favorites (`isFavorite = false`).
    * 4. **Updates Favorite Status:** Updates the favorite status of restaurants in the cached data.
    *    - It uses the list of favorite restaurants retrieved earlier to set the `isFavorite` flag to `true` for those restaurants in the cache using `restaurantsDao.updateAll()`.
    *    - `PartialLocalRestaurant` is used for efficient updating, only specifying the id and the new `isFavorite` state.
    * 5. **Error Handling:** Handles potential errors during the process:
    *    - **Network Errors:** Catches `UnknownHostException`, `ConnectException`, and `HttpException` (common network-related errors).
    *      - If any of these errors occur and the local database is empty (`restaurantsDao.getAll().isEmpty()`), it throws a generic "Something went wrong." exception, indicating a critical failure to retrieve or store data.
    *      - If any of these errors occur and the local database is NOT empty, then function returns normally. This imply that the user will see the older data.
    *    - **Other Errors:** Any other exceptions are re-thrown, indicating an unexpected issue.
    *
    * @throws Exception Throws a generic "Something went wrong." exception if a network error occurs and the local database is empty.
    * @throws Exception Re-throws any other encountered exception that is not a handled network error.
    */
   suspend fun cacheRestaurants() =
      withContext(dispatcher) {
         try {

            //refresh cache
            val remoteRestaurants = restApi.getRestaurants()

            //du lieu yeu thich
            val favoriteRestaurants = restaurantsDao.getAllFavorite()

            //cache data
            restaurantsDao.cacheRestaurants(remoteRestaurants.map {
               LocalRestaurant(it.id, it.title, it.description, false)
            })

            //update du lieu yeu thich
            restaurantsDao.updateAll(
               favoriteRestaurants.map {
                  PartialLocalRestaurant(it, true)
               }
            )
         } catch (e: Exception) {
            when (e) {
               is UnknownHostException,
               is ConnectException,
               is HttpException,
                  -> {
                  if (restaurantsDao.getAll().isEmpty())
                     throw Exception("Kết nối Internet khi dùng app lần đầu")
               }

               else -> throw e
            }
         }
      }

   /**
    * Get local restaurants
    */
   suspend fun getLocalRestaurants() =
      withContext(dispatcher) {
         restaurantsDao.getAll().map {
            Restaurant(it.id, it.title, it.description, it.isFavourite)
         }
      }

   /**
    * Toggles the favorite status of a restaurant in the local data source.
    *
    * This function updates the `isFavorite` property of a restaurant in the local database
    * (presumably using Room or a similar persistence library). It operates within the IO
    * dispatcher to ensure database operations don't block the main thread.
    *
    * @param id The unique identifier of the restaurant to update.
    * @param value The new favorite status to set for the restaurant (true if favorite, false otherwise).
    *
    * @throws Exception if any error occurs during the database update.
    */
   suspend fun toggleFavoriteRestaurant(
      id: Int,
      value: Boolean,
   ) = withContext(dispatcher) {
      restaurantsDao.update(
         PartialLocalRestaurant(id = id, isFavorite = value)
      )
   }

   /**
    * Retrieves a restaurant from the local database by its ID.
    *
    * This function queries the local database using the `restaurantsDao` to find a restaurant
    * with the specified ID. If a restaurant is found, it is transformed into a `Restaurant`
    * data class object and returned.
    *
    * This function is a suspend function and performs its operation on the IO dispatcher,
    * making it suitable for database operations.
    *
    * @param id The ID of the restaurant to retrieve.
    * @return A [Restaurant] object if a restaurant with the given ID exists in the database,
    *         otherwise null.
    */
   suspend fun getLocalRestaurant(id: Int) =
      withContext(dispatcher) {
         restaurantsDao.getById(id)?.let {
            Restaurant(
               it.id, it.title, it.description, it.isFavourite
            )
         }
      }
}

