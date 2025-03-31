package com.ad.restaurant.restaurants

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.ad.restaurant.restaurants.presentation.details.RestaurantScreen
import com.ad.restaurant.restaurants.presentation.details.RestaurantViewModel
import com.ad.restaurant.restaurants.presentation.list.RestaurantsScreen
import com.ad.restaurant.restaurants.presentation.list.RestaurantsViewModel

@Composable
fun RestaurantsNavHost(
   navController: NavHostController,
) {
   NavHost(
      navController,
      startDestination = "restaurants"
   ) {

      composable(route = "restaurants") {
         val vm: RestaurantsViewModel = hiltViewModel()

         RestaurantsScreen(
            onItemClick = { id ->
               navController.navigate("restaurants/$id")
            },
            uiState = vm.uiState.value,
            onFavoriteClick = { id, oldValue ->
               vm.toggleFavoriteRestaurant(id, oldValue)
            }
         )
      }

      composable(
         route = "restaurants/{restaurant_id}",
         arguments = listOf(
            navArgument("restaurant_id") {
               type = NavType.IntType
            }
         ),
         deepLinks = listOf(
            navDeepLink {
               uriPattern = "www.restaurantsapp.details.com/{restaurant_id}"
            }
         )
      ) {
         val vm: RestaurantViewModel = hiltViewModel()
         RestaurantScreen(
            uiState = vm.uiState.value
         )
      }
   }
}