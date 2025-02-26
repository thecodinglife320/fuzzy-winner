package com.example.busschedule

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.busschedule.ui.navigation.BusScheduleNavHost

@Composable
fun BusScheduleApp(navController: NavHostController = rememberNavController()) {
   BusScheduleNavHost(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusScheduleTopAppBar(
   title: String,
   canNavigateBack: Boolean,
   navigateUp: () -> Unit,
   modifier: Modifier = Modifier
) {
   if (canNavigateBack) {
      TopAppBar(
         title = { Text(title) },
         navigationIcon = {
            IconButton(onClick = navigateUp) {
               Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                  contentDescription = stringResource(
                     R.string.back
                  )
               )
            }
         },
         modifier = modifier
      )
   } else {
      TopAppBar(
         title = { Text(title) },
         modifier = modifier
      )
   }
}