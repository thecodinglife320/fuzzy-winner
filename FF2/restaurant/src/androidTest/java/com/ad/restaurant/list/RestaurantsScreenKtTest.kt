package com.ad.restaurant.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.ad.restaurant.restaurants.domain.DummyRestaurants
import com.ad.restaurant.restaurants.presentation.Description
import com.ad.restaurant.restaurants.presentation.list.RestaurantsScreen
import com.ad.restaurant.restaurants.presentation.list.RestaurantsScreenState
import com.ad.restaurant.ui.theme.FF2Theme
import org.junit.Rule
import org.junit.Test

class RestaurantsScreenKtTest {

   @get:Rule
   val testRule: ComposeContentTestRule = createComposeRule()

   @Test
   fun initialState_isRendered() {
      testRule.setContent {
         FF2Theme {
            RestaurantsScreen(
               uiState = RestaurantsScreenState(
                  restaurants = emptyList(),
                  isLoading = true,
               ),
               onItemClick = { _ -> },
               onFavoriteClick = { _, _ -> }
            )
         }
      }
      testRule.onNodeWithContentDescription(
         Description.RESTAURANTS_LOADING
      ).assertIsDisplayed()
   }

   @Test
   fun stateWithContent_isRendered() {
      testRule.setContent {
         FF2Theme {
            RestaurantsScreen(
               uiState = RestaurantsScreenState(
                  restaurants = DummyRestaurants.restaurants,
                  isLoading = false,
               ),
               onItemClick = { _ -> },
               onFavoriteClick = { _, _ -> }
            )
         }
      }

      testRule.onNodeWithText(
         DummyRestaurants.restaurants[0].title
      ).assertIsDisplayed()

      testRule.onNodeWithText(
         DummyRestaurants.restaurants[1].title
      ).assertIsDisplayed()

      testRule.onNodeWithContentDescription(
         Description.RESTAURANTS_LOADING
      ).assertDoesNotExist()
   }

   @Test
   fun stateWithContent_ClickOnItem_isRegistered() {
      val restaurants = DummyRestaurants.restaurants
      val targetRestaurant = restaurants[0]
      testRule.setContent {
         FF2Theme {
            RestaurantsScreen(
               uiState = RestaurantsScreenState(
                  restaurants = restaurants,
                  isLoading = false
               ),
               onFavoriteClick = { _, _ -> },
               onItemClick = { id ->
                  assert(id == targetRestaurant.id)
               })
         }
      }
      testRule.onNodeWithText(targetRestaurant.title)
         .performClick()
   }
}