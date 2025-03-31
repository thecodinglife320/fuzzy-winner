package com.ad.restaurant.restaurants.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ad.restaurant.restaurants.domain.Restaurant
import com.ad.restaurant.restaurants.domain.RestaurantsSample
import com.ad.restaurant.restaurants.domain.RestaurantsSample.restaurant
import com.ad.restaurant.ui.shared.RestaurantDetails
import com.ad.restaurant.ui.shared.RestaurantIcon

@Preview
@Composable
fun RestaurantsScreen(
   uiState: RestaurantsScreenState = RestaurantsScreenState(
      restaurants = RestaurantsSample.restaurants,
      isLoading = false,
      error = null
   ),
   onItemClick: (id: Int) -> Unit = {},
   onFavoriteClick: (id: Int, oldValue: Boolean) -> Unit = { _, _ -> },
) {

   Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier.fillMaxSize()
   ) {

      //loading
      if (uiState.isLoading) CircularProgressIndicator()

      //loi
      uiState.error?.let { Text(it) }

      //danh sach
      LazyColumn(
         contentPadding = PaddingValues(8.dp),
      ) {
         items(
            items = uiState.restaurants
         ) {
            RestaurantItem(
               item = it,
               modifier = Modifier.padding(8.dp),
               onFavouriteClick = {
                  onFavoriteClick(it.id, it.isFavourite)
               },
               onItemClick = onItemClick
            )
         }
      }
   }
}

@Preview
@Composable
fun RestaurantItem(
   modifier: Modifier = Modifier,
   item: Restaurant = restaurant,
   onFavouriteClick: (restaurant: Restaurant) -> Unit = {},
   onItemClick: (id: Int) -> Unit = { _ -> },
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