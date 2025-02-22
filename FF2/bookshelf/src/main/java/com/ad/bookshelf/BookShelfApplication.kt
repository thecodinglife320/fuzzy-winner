package com.ad.bookshelf

import android.app.Application

class BookShelfApplication : Application() {

   lateinit var container: AppContainer

   override fun onCreate() {
      super.onCreate()
      container = DefaultAppContainer()
   }
}