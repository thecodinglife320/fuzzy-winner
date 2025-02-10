package com.ad.ff2

 import android.annotation.SuppressLint
 import android.os.Bundle
 import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
 import androidx.activity.enableEdgeToEdge
 import com.ad.ff2.superhero.HeroApp
 import com.ad.ff2.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           SuperheroesTheme {
              HeroApp()
            }
        }
    }
}