package com.ad.restaurant

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ad.restaurant.restaurant.RestaurantScreen
import com.ad.restaurant.restaurants.RestaurantsScreen

@Composable
fun RestaurantsApp() {
   val navController = rememberNavController()
   @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
   Scaffold { _ ->
      NavHost(navController, startDestination = "restaurants") {
         composable(route = "restaurants") {
            RestaurantsScreen(
               onItemClick = { id ->
                  navController.navigate("restaurants/$id")
               }
            )
         }
         composable(
            route = "restaurants/{restaurant_id}",
            arguments = listOf(
               navArgument("restaurant_id") {
                  type = NavType.IntType
               }
            )
         ) {
            RestaurantScreen()
         }
      }
   }
}