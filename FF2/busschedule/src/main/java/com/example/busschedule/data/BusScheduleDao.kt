package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import com.example.busschedule.data.model.BusSchedule
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {

   @Query("SELECT * from Schedule WHERE stop_name = :stopName")
   fun getScheduleFor(stopName: String): Flow<List<BusSchedule>>

   @Query("SELECT * from Schedule ORDER BY arrival_time ASC")
   fun getFullSchedule(): Flow<List<BusSchedule>>
}