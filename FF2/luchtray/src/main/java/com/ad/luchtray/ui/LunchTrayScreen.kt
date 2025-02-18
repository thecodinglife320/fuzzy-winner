package com.ad.luchtray.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ad.luchtray.R
import com.ad.luchtray.datasource.DataSource
import com.ad.luchtray.ui.screen.EntreeMenuScreen
import com.ad.luchtray.ui.screen.StartOrderScreen
import com.ad.luchtray.ui.stateholder.OrderViewModel
import com.ad.luchtray.ui.theme.AppTheme

enum class LunchTrayRoute(@StringRes val title: Int) {
   Start(R.string.app_name),
   EntreeMenu(R.string.choose_entree),
   SideDishMenu(R.string.choose_side_dish),
   AccompanimentMenu(R.string.choose_accompaniment),
   Checkout(R.string.order_checkout)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LunchTrayAppBar(
   currentScreen: LunchTrayRoute,
   canNavigateBack: Boolean,
   navigateUp: () -> Unit,
   modifier: Modifier = Modifier
) {

   TopAppBar(
      title = { Text(stringResource(currentScreen.title)) },
      colors = TopAppBarDefaults.mediumTopAppBarColors(
         containerColor = MaterialTheme.colorScheme.primaryContainer
      ),
      modifier = modifier,
      navigationIcon = {
         if (canNavigateBack) {
            IconButton(onClick = navigateUp) {
               Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                  contentDescription = stringResource(R.string.back_button)
               )
            }
         }
      }
   )
}

@Composable
fun LunchTrayApp(
   navController: NavHostController = rememberNavController(),
   viewModel: OrderViewModel = viewModel()
) {

   val backStackEntry by navController.currentBackStackEntryAsState()
   val currentScreen = LunchTrayRoute.valueOf(
      backStackEntry?.destination?.route ?: LunchTrayRoute.Start.name
   )

   Scaffold(
      topBar = {
         LunchTrayAppBar(
            canNavigateBack = navController.previousBackStackEntry != null,
            currentScreen = currentScreen,
            navigateUp = { navController.navigateUp() },
         )
      }
   ) { innerPadding ->
      val uiState by viewModel.uiStateFlow.collectAsState()

      NavHost(
         navController = navController,
         startDestination = LunchTrayRoute.Start.name,
         modifier = Modifier.padding(innerPadding)
      ) {

         //start screen
         composable(route = LunchTrayRoute.Start.name) {
            StartOrderScreen(
               onStartOrderButtonClicked = { navController.navigate(LunchTrayRoute.EntreeMenu.name) },
               modifier = Modifier.fillMaxSize()
            )
         }

         //entree menu
         composable(route = LunchTrayRoute.EntreeMenu.name) {
            EntreeMenuScreen(
               options = DataSource.entreeMenuItems,
               onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
               onNextButtonClicked = { navController.navigate(LunchTrayRoute.SideDishMenu.name) },
               onSelectionChanged = { entree -> viewModel.updateEntree(entree) },
               modifier = Modifier.fillMaxSize()
            )
         }

         //side dish menu
         composable(route = LunchTrayRoute.SideDishMenu.name) {

         }

         //Accompaniment menu
         composable(route = LunchTrayRoute.AccompanimentMenu.name) {

         }

         //checkout
         composable(route = LunchTrayRoute.Checkout.name) {

         }
      }
   }
}

private fun cancelOrderAndNavigateToStart(
   viewModel: OrderViewModel,
   navController: NavHostController
) {
   viewModel.resetOrder()
   navController.popBackStack(LunchTrayRoute.Start.name, false)
}

@Preview(showSystemUi = true)
@Composable
fun LunchTrayAppPreview() {
   AppTheme {
      LunchTrayApp()
   }
}