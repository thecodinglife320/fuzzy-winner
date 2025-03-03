package com.ad.monngonmoingay

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ad.monngonmoingay.data.model.ErrorMessage
import com.ad.monngonmoingay.ui.home.HomeDestination
import com.ad.monngonmoingay.ui.home.HomeScreen
import com.ad.monngonmoingay.ui.login.SignInDestination
import com.ad.monngonmoingay.ui.login.SignInScreen
import com.ad.monngonmoingay.ui.signup.SignUpDestination
import com.ad.monngonmoingay.ui.signup.SignUpScreen
import com.ad.monngonmoingay.ui.theme.FF2Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setSoftInputMode()
      setContent {
         val scope = rememberCoroutineScope()
         val snackBarHostState = remember { SnackbarHostState() }
         val navController = rememberNavController()

         FF2Theme {
            Surface(
               modifier = Modifier.fillMaxSize(),
               color = MaterialTheme.colorScheme.background
            ) {
               Scaffold(
                  Modifier.fillMaxSize(),
                  snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
               ) { innerPadding ->
                  NavHost(
                     navController = navController,
                     startDestination = HomeDestination.ROUTE,
                     modifier = Modifier.padding(innerPadding)
                  ) {
                     composable(SignInDestination.ROUTE) {
                        SignInScreen(
                           openHomeScreen = {
                              navController.navigate(HomeDestination.ROUTE) {
                                 launchSingleTop = true
                              }
                           },
                           openSignUpScreen = {
                              navController.navigate(SignUpDestination.ROUTE) {
                                 launchSingleTop = true
                              }
                           },
                           showErrorSnackBar = { errorMessage ->
                              val message = getErrorMessage(errorMessage)
                              scope.launch { snackBarHostState.showSnackbar(message) }
                           },
                        )
                     }
                     composable(SignUpDestination.ROUTE) {
                        SignUpScreen(
                           openHomeScreen = {
                              navController.navigate(HomeDestination.ROUTE) {
                                 launchSingleTop = true
                              }
                           },
                           showErrorSnackBar = { errorMessage ->
                              val message = getErrorMessage(errorMessage)
                              scope.launch {
                                 snackBarHostState.showSnackbar(message)
                              }
                           },
                        )
                     }
                     composable(HomeDestination.ROUTE) {
                        HomeScreen(
                           openSettingsScreen = {},
                        )
                     }
                  }
               }
            }
         }
      }
   }

   private fun setSoftInputMode() {
      window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
   }

   private fun getErrorMessage(error: ErrorMessage): String {
      return when (error) {
         is ErrorMessage.StringError -> error.message
         is ErrorMessage.IdError -> this@MainActivity.getString(error.message)
      }
   }
}

