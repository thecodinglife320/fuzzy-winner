package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.busschedule.data.model.BusSchedule

@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
abstract class BusScheduleDatabase : RoomDatabase() {

   abstract fun busScheduleDao(): BusScheduleDao

   companion object {

      @Volatile
      private var Instance: BusScheduleDatabase? = null

      fun getDatabase(context: Context): BusScheduleDatabase {
         return Instance ?: synchronized(this) {

            // Use createFromAsset() if the database is in the assets folder
            Room.databaseBuilder(
               context.applicationContext,
               BusScheduleDatabase::class.java,
               "bus_schedule.db"
            )
               .createFromAsset("database/bus_schedule.db")
               .build()
               .also { Instance = it }
         }
      }

   }
}