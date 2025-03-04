package com.ad.monngonmoingay

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ad.monngonmoingay.data.model.ErrorMessage
import com.ad.monngonmoingay.ui.home.HomeDestination
import com.ad.monngonmoingay.ui.home.HomeScreen
import com.ad.monngonmoingay.ui.login.SignInDestination
import com.ad.monngonmoingay.ui.login.SignInScreen
import com.ad.monngonmoingay.ui.signup.SignUpDestination
import com.ad.monngonmoingay.ui.signup.SignUpScreen
import kotlinx.coroutines.launch

@Composable
fun MonNgonApp() {

   val scope = rememberCoroutineScope()
   val snackBarHostState = remember { SnackbarHostState() }
   val navController = rememberNavController()
   val navBackStackEntry by navController.currentBackStackEntryAsState()
   val currentScreen = navBackStackEntry?.destination?.route ?: HomeDestination.ROUTE

   Scaffold(
      topBar = {
         MonNgonAppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() }
         )
      },
      modifier = Modifier.fillMaxSize(),
      snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
   ) { innerPadding ->
      NavHost(
         navController = navController,
         startDestination = HomeDestination.ROUTE,
         modifier = Modifier.padding(innerPadding)
      ) {
         composable(SignInDestination.ROUTE) {
            val context = LocalContext.current
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
                  val message = when (errorMessage) {
                     is ErrorMessage.StringError -> errorMessage.message
                     is ErrorMessage.IdError -> context.getString(errorMessage.message)
                  }
                  scope.launch { snackBarHostState.showSnackbar(message) }
               },
            )
         }
         composable(SignUpDestination.ROUTE) {
            val context = LocalContext.current
            SignUpScreen(
               openHomeScreen = {
                  navController.navigate(HomeDestination.ROUTE) {
                     launchSingleTop = true
                  }
               },
               showErrorSnackBar = { errorMessage ->
                  val message = when (errorMessage) {
                     is ErrorMessage.StringError -> errorMessage.message
                     is ErrorMessage.IdError -> context.getString(errorMessage.message)
                  }
                  scope.launch {
                     snackBarHostState.showSnackbar(message)
                  }
               },
            )
         }
         composable(HomeDestination.ROUTE) {
            HomeScreen()
         }
      }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MonNgonAppBar(
   currentScreen: String,
   canNavigateBack: Boolean,
   navigateUp: () -> Unit,
   modifier: Modifier = Modifier
) {
   TopAppBar(
      title = { Text(text = currentScreen) },
      modifier = modifier,
      navigationIcon = {
         if (canNavigateBack) {
            IconButton(onClick = navigateUp) {
               Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                  contentDescription = ""
               )
            }
         }
      }
   )
}