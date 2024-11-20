package com.learning.ad.ff

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class BoundService : Service() {
   private val myBinder = MyLocalBinder()

   inner class MyLocalBinder :Binder(){
      fun getService() = this@BoundService
   }

   override fun onBind(intent: Intent): IBinder = myBinder


}