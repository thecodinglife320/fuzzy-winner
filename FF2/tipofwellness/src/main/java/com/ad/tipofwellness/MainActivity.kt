package com.ad.tipofwellness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ad.tipofwellness.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         AppTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
               Greeting(
                  name = "Android",
                  modifier = Modifier.padding(innerPadding)
               )
            }
         }
      }
   }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
   Text(
      text = "Hello $name!",
      modifier = modifier
   )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
   CenterAlignedTopAppBar(
      title = {
         Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.displayLarge
         )
      },
      modifier = modifier
   )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
   AppTheme {
      Greeting("Android")
   }
}