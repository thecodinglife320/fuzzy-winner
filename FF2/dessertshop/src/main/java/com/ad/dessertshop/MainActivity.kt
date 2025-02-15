package com.ad.dessertshop

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ad.dessertshop.ui.compose.DessertShopApp
import com.ad.dessertshop.ui.theme.FF2Theme

class MainActivity : ComponentActivity() {

   companion object {
      const val TAG = "MainActivity"
   }

   @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
   override fun onCreate(savedInstanceState: Bundle?) {
      enableEdgeToEdge()
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         FF2Theme {
            DessertShopApp()
         }
      }
      Log.d(TAG, "onCreate Called")
   }

   override fun onStart() {
      super.onStart()
      Log.d(TAG, "onStart Called")
   }

   override fun onResume() {
      super.onResume()
      Log.d(TAG, "onResume Called")
   }

   override fun onRestart() {
      super.onRestart()
      Log.d(TAG, "onRestart Called")
   }

   override fun onPause() {
      super.onPause()
      Log.d(TAG, "onPause Called")
   }

   override fun onStop() {
      super.onStop()
      Log.d(TAG, "onStop Called")
   }

   override fun onDestroy() {
      super.onDestroy()
      Log.d(TAG, "onDestroy Called")
   }

}