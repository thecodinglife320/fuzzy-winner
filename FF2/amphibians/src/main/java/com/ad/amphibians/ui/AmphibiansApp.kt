//package com.ad.amphibians.ui
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.CenterAlignedTopAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.material3.TopAppBarScrollBehavior
//import androidx.compose.runtime.ComposableA
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.input.nestedscroll.nestedScroll
//import androidx.compose.ui.res.stringResource
//
//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun AmphibiansApp() {
//   val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
//   Scaffold(
//      modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//      topBar = { TopAppBar(scrollBehavior = scrollBehavior) }
//   ) {
//      val marsViewModel: AmphibiansViewModel = viewModel(factory = MarsViewModel.Factory)
//      HomeScreen(
//         marsUiState = marsViewModel.marsUiState,
//         modifier = Modifier.fillMaxSize(),
//         retryAction = { marsViewModel.getMarsPhotos() }
//      )
//   }
//}
//
//@Composable
//fun TopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
//   CenterAlignedTopAppBar(
//      scrollBehavior = scrollBehavior,
//      title = {
//         Text(
//            text = stringResource(R.string.app_name),
//            style = MaterialTheme.typography.headlineSmall,
//         )
//      },
//      modifier = modifier
//   )
//}