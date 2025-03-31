package com.ad.restaurant.restaurants.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RestaurantsDao {

   @Query("select * from restaurants")
   suspend fun getAll(): List<LocalRestaurant>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun cacheRestaurants(restaurants: List<LocalRestaurant>)

   @Update(entity = LocalRestaurant::class)
   suspend fun update(partialRestaurant: PartialLocalRestaurant)

   @Update(entity = LocalRestaurant::class)
   suspend fun updateAll(partialRestaurants: List<PartialLocalRestaurant>)

   @Query("SELECT r_id FROM restaurants WHERE is_favourite = 1")
   suspend fun getAllFavorite(): List<Int>

   @Query("SELECT * FROM restaurants WHERE r_id = :id")
   suspend fun getById(id: Int): LocalRestaurant?

}
