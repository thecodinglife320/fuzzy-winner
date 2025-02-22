package com.ad.amphibians

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ad.amphibians.ui.AmphibiansApp
import com.ad.amphibians.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
   @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         AppTheme {
            AmphibiansApp()
         }
      }
   }
}
