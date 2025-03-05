package com.ad.monngonmoingay

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ad.monngonmoingay.data.model.ErrorMessage
import com.ad.monngonmoingay.ui.home.HomeDestination
import com.ad.monngonmoingay.ui.home.HomeScreen
import com.ad.monngonmoingay.ui.login.SignInDestination
import com.ad.monngonmoingay.ui.login.SignInScreen
import com.ad.monngonmoingay.ui.setting.SettingDestination
import com.ad.monngonmoingay.ui.setting.SettingsScreen
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
   val shouldShowSettings = !(currentScreen == SignInDestination.ROUTE ||
        currentScreen == SignUpDestination.ROUTE)

   Scaffold(
      topBar = {
         MonNgonAppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() },
            actionSettingIcon = {
               navController.navigate(SettingDestination.ROUTE) {
                  launchSingleTop = true
               }
            },
            shouldShowSettings = shouldShowSettings,
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

         //sign in screen
         composable(SignInDestination.ROUTE) {
            val context = LocalContext.current
            SignInScreen(
               restartApp = {
                  navController.popBackStack(HomeDestination.ROUTE, false)
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

         //sign up screen
         composable(SignUpDestination.ROUTE) {
            val context = LocalContext.current
            SignUpScreen(
               restartApp = {
                  navController.popBackStack(HomeDestination.ROUTE, false)
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

         //home screen
         composable(HomeDestination.ROUTE) {
            HomeScreen()
         }

         //setting screen
         composable(SettingDestination.ROUTE) {
            SettingsScreen(
               openHomeScreen = {
                  navController.popBackStack(HomeDestination.ROUTE, false)
               },
               openSignInScreen = {
                  navController.navigate(SignInDestination.ROUTE) {
                     launchSingleTop = true
                  }
               },
            )
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
   actionSettingIcon: () -> Unit,
   shouldShowSettings: Boolean,
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
      },
      actions = {
         if (shouldShowSettings)
            IconButton(onClick = { actionSettingIcon() }) {
               Icon(
                  imageVector = Icons.Filled.Settings,
                  contentDescription = stringResource(R.string.settings)
               )
            }
      },
   )
}