package com.example.busschedule

import android.app.Application
import com.example.busschedule.data.AppContainer
import com.example.busschedule.data.OfflineDataContainer

class BusScheduleApplication : Application() {
   lateinit var container: AppContainer

   override fun onCreate() {
      super.onCreate()
      container = OfflineDataContainer(this)
   }
}