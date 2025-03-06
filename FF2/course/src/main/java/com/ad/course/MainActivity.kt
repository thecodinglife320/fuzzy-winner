package com.ad.course

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ad.course.ui.theme.AppTheme

@SuppressWarnings("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         AppTheme {
            Scaffold(
               topBar = { AppBar() },
            ) { innerPadding ->
               TopicCardGrid(DataSource.topics, Modifier.padding(innerPadding))
            }
         }
      }
   }
}
