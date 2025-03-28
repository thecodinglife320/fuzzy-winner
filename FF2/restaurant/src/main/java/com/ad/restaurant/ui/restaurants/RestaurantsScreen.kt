package com.ad.restaurant.ui.restaurants

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ad.restaurant.data.model.Restaurant
import com.ad.restaurant.ui.shared.RestaurantDetails
import com.ad.restaurant.ui.shared.RestaurantIcon

@Composable
fun RestaurantsScreen(onItemClick: (id: Int) -> Unit) {

   val vm: RestaurantsViewModel = viewModel()
   LazyColumn(
      contentPadding = PaddingValues(8.dp),
   ) {
      items(
         items = vm.uiState
      ) {
         RestaurantItem(
            item = it,
            modifier = Modifier.padding(8.dp),
            onFavouriteClick = {
               vm.toggleFavoriteRestaurant(it.id, it.isFavourite)
            },
            onItemClick = onItemClick
         )
      }
   }
}

@Composable
fun RestaurantItem(
   modifier: Modifier = Modifier,
   item: Restaurant = Restaurant(0, "", "", false),
   onFavouriteClick: (restaurant: Restaurant) -> Unit = {},
   onItemClick: (id: Int) -> Unit,
) {

   val icon =
      if (item.isFavourite) Icons.Filled.Favorite
      else Icons.Filled.FavoriteBorder

   Card(
      elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
      modifier = modifier
         .clickable {
            onItemClick(item.id)
         }
   ) {
      Row(
         verticalAlignment = Alignment.CenterVertically,
         modifier = modifier
      ) {

         RestaurantIcon(
            modifier = Modifier
               .weight(0.15f)
         )

         RestaurantDetails(
            Modifier.weight(0.7f),
            title = item.title,
            description = item.description
         )

         RestaurantIcon(
            modifier = Modifier
               .weight(0.15f),
            icon = icon,
            onClick = { ->
               onFavouriteClick(item)
            },
         )
      }
   }
}