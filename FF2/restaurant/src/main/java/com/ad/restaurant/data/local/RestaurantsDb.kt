package com.ad.restaurant.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ad.restaurant.data.model.Restaurant

@Database(
   entities = [Restaurant::class],
   version = 2,
   exportSchema = false
)
abstract class RestaurantsDb : RoomDatabase() {

   abstract val dao: RestaurantsDao

   companion object {

      @Volatile
      private var INSTANCE: RestaurantsDao? = null

      fun getDaoInstance(context: Context): RestaurantsDao {
         synchronized(this) {
            var instance = INSTANCE
            if (instance == null) {
               instance = buildDatabase(context).dao
               INSTANCE = instance
            }
            return instance
         }
      }

      private fun buildDatabase(context: Context) =
         Room.databaseBuilder(
            context = context.applicationContext,
            klass = RestaurantsDb::class.java,
            name = "restaurants_db"
         )
            .fallbackToDestructiveMigration()
            .build()
   }

}