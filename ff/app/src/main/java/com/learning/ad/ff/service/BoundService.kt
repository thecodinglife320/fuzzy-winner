package com.learning.ad.ff.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.*
import java.util.*

class BoundService : Service() {
   private val myBinder = MyLocalBinder()

   inner class MyLocalBinder :Binder(){
      fun getService() = this@BoundService
   }

   override fun onBind(intent: Intent): IBinder = myBinder
   fun getCurrentTime(): String {
      val dateFormat = SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US)
      return dateFormat.format(Date())}
}