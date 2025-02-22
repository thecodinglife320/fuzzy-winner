package com.ad.amphibians.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ad.amphibians.R
import com.ad.amphibians.ui.screens.HomeScreen
import com.ad.amphibians.ui.stateholder.AmphibiansViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AmphibiansApp() {
   val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
   Scaffold(
      modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
      topBar = { AmphibiansAppBar(scrollBehavior = scrollBehavior) }
   ) {
      val amphibiansViewModel: AmphibiansViewModel =
         viewModel(factory = AmphibiansViewModel.Factory)
      HomeScreen(
         amphibiansUiState = amphibiansViewModel.amphibiansUiState,
         modifier = Modifier.fillMaxSize(),
         retryAction = { amphibiansViewModel.getAmphibians() }
      )
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {

   TopAppBar(
      title = { Text(stringResource(R.string.app_name)) },
      colors = TopAppBarDefaults.mediumTopAppBarColors(
         containerColor = MaterialTheme.colorScheme.primaryContainer
      ),
      modifier = modifier,
      scrollBehavior = scrollBehavior
   )
}