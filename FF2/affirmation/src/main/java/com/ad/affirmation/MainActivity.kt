package com.ad.affirmation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ad.affirmation.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
   @OptIn(ExperimentalMaterial3Api::class)
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         AppTheme {
            Scaffold(
               topBar = {
                  CenterAlignedTopAppBar(
                     title = {
                        Text(text = stringResource(id = R.string.app_name))
                     },
                  )
               },
               modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
               AffirmationList(Datasource.affirmations, Modifier.padding(innerPadding))
            }
         }
      }
   }
}