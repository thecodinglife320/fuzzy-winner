package com.learning.ad.ff.service
import android.app.Service
import android.content.Intent
import android.os.*
import android.util.*
const val TAG1 = "RemoveService"
class RemoteService : Service() {
   private val myMessenger = Messenger(SimpleHandle())
   class SimpleHandle:Handler(Looper.getMainLooper()){
      override fun handleMessage(msg: Message) {
         msg.data.getString("MyString")?.let { Log.d(TAG1, it) }
      }
   }
   override fun onBind(intent: Intent): IBinder = myMessenger.binder
}