package com.ad.restaurant.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ad.restaurant.data.model.PartialRestaurant
import com.ad.restaurant.data.model.Restaurant

@Dao
interface RestaurantsDao {

   @Query("select * from restaurants")
   suspend fun getAll(): List<Restaurant>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun cacheRestaurants(restaurants: List<Restaurant>)

   @Update(entity = Restaurant::class)
   suspend fun update(partialRestaurant: PartialRestaurant)

   @Update(entity = Restaurant::class)
   suspend fun updateAll(partialRestaurants: List<PartialRestaurant>)

   @Query("SELECT r_id FROM restaurants WHERE is_favourite = 1")
   suspend fun getAllFavorite(): List<Int>

   @Query("SELECT * FROM restaurants WHERE r_id = :id")
   suspend fun getById(id: Int): Restaurant?

}
