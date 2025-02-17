package com.ad.cupcake.ui

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ad.cupcake.R
import com.ad.cupcake.data.DataSource
import com.ad.cupcake.ui.compose.OrderSummaryScreen
import com.ad.cupcake.ui.compose.SelectOptionScreen
import com.ad.cupcake.ui.compose.StartOrderScreen
import com.ad.cupcake.ui.stateholder.OrderViewModel
import com.ad.cupcake.ui.theme.FF2Theme

enum class CupcakeScreen(@StringRes val title: Int) {
   Start(R.string.app_name),
   Flavor(R.string.choose_flavor),
   Pickup(R.string.choose_pickup_date),
   Summary(R.string.order_summary)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CupcakeAppBar(
   currentScreen: CupcakeScreen,
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
fun CupcakeApp(
   viewModel: OrderViewModel = viewModel(),
   navController: NavHostController = rememberNavController()
) {

   val backStackEntry by navController.currentBackStackEntryAsState()
   val currentScreen = CupcakeScreen.valueOf(
      backStackEntry?.destination?.route ?: CupcakeScreen.Start.name
   )

   Scaffold(
      topBar = {
         CupcakeAppBar(
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() },
            currentScreen = currentScreen,
         )
      }
   ) { innerPadding ->

      val uiState by viewModel.uiStateFlow.collectAsState()

      NavHost(
         navController = navController,
         startDestination = CupcakeScreen.Start.name,
         modifier = Modifier.padding(innerPadding)
      ) {

         composable(route = CupcakeScreen.Start.name) {
            StartOrderScreen(
               quantityOptions = DataSource.quantityOptions,
               modifier = Modifier
                  .fillMaxSize()
                  .padding(dimensionResource(R.dimen.padding_medium)),
               onNextButtonClicked = { quantity ->
                  viewModel.setQuantity(quantity)
                  navController.navigate(CupcakeScreen.Flavor.name)
               }
            )
         }

         composable(route = CupcakeScreen.Flavor.name) {
            val context = LocalContext.current
            SelectOptionScreen(
               subtotal = uiState.price,
               options = DataSource.flavors.map { id -> context.resources.getString(id) },
               onSelectionChanged = { flavor -> viewModel.setFlavor(flavor) },
               modifier = Modifier.fillMaxHeight(),
               onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
               onNextButtonClicked = { navController.navigate(CupcakeScreen.Pickup.name) },
            )
         }

         composable(route = CupcakeScreen.Pickup.name) {
            SelectOptionScreen(
               subtotal = uiState.price,
               options = uiState.pickupOptions,
               onSelectionChanged = { viewModel.setDate(it) },
               modifier = Modifier.fillMaxHeight(),
               onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
               onNextButtonClicked = { navController.navigate(CupcakeScreen.Summary.name) }
            )
         }

         composable(route = CupcakeScreen.Summary.name) {

            val context = LocalContext.current

            OrderSummaryScreen(
               orderUiState = uiState,
               modifier = Modifier.fillMaxHeight(),
               onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
               onSendButtonClicked = { subject: String, summary: String ->
                  shareOrder(context, subject, summary)
               },
            )
         }
      }
   }
}

private fun cancelOrderAndNavigateToStart(
   viewModel: OrderViewModel,
   navController: NavHostController
) {
   viewModel.resetOrder()
   navController.popBackStack(CupcakeScreen.Start.name, false)
}

private fun shareOrder(context: Context, subject: String, summary: String) {
   val intent = Intent(Intent.ACTION_SEND).apply {
      type = "text/plain"
      putExtra(Intent.EXTRA_SUBJECT, subject)
      putExtra(Intent.EXTRA_TEXT, summary)
   }
   context.startActivity(
      Intent.createChooser(
         intent,
         context.getString(R.string.new_cupcake_order)
      )
   )
}

@Preview
@Composable
private fun Preview() {
   FF2Theme {
      CupcakeApp()
   }
}
