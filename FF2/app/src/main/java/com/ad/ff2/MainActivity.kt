package com.ad.ff2

 import android.annotation.SuppressLint
 import android.os.Bundle
 import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
 import androidx.activity.enableEdgeToEdge
 import com.ad.ff2.ui.theme.WoofTheme
 import com.ad.ff2.woof.WoofApp

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           WoofTheme {
              WoofApp()
            }
        }
    }
}