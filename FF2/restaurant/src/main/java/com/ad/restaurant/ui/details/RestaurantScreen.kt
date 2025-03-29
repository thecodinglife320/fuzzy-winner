package com.ad.restaurant.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ad.restaurant.ui.shared.RestaurantDetails
import com.ad.restaurant.ui.shared.RestaurantIcon

@Preview
@Composable
fun RestaurantScreen() {

   val vm: RestaurantViewModel = viewModel()
   val uiState = vm.uiState.value

   Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier.fillMaxSize()
   ) {

      //loading
      if (uiState.isLoading) CircularProgressIndicator()

      //show error
      uiState.error?.let { Text(it) }

      //present data
      uiState.restaurant?.let {
         Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
               .fillMaxSize()
               .padding(16.dp)
         ) {

            RestaurantIcon()

            RestaurantDetails(
               title = it.title,
               description = it.description,
               horizontalAlignment = Alignment.CenterHorizontally
            )

            if (it.isFavourite) RestaurantIcon(
               icon = Icons.Filled.Favorite,
               onClick = {}
            )
         }
      }
   }
}