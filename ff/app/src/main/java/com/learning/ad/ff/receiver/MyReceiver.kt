package com.learning.ad.ff.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.*

class MyReceiver : BroadcastReceiver() {

   override fun onReceive(context: Context, intent: Intent) {
      Toast.makeText(context, "Broadcast Intent Detected. ${intent.action}", Toast.LENGTH_LONG).show()
   }
}