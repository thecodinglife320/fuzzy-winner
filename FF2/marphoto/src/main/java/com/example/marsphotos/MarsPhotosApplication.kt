package com.example.marsphotos

import android.app.Application

class MarsPhotosApplication : Application() {

   lateinit var container: AppContainer

   override fun onCreate() {
      super.onCreate()
      container = DefaultAppContainer()
   }
}