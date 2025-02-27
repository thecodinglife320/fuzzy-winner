package com.ad.playvideo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.ad.playvideo.ui.theme.FF2Theme

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         FF2Theme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
               YoutubePlayer(
                  youtubeVideoId = "9j8PyBhLUaE",
                  lifecycleOwner = LocalLifecycleOwner.current,
                  Modifier.padding(innerPadding)
               )
            }
         }
      }
   }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
   FF2Theme {
      //YouTubeVideoPlayer()
   }
}