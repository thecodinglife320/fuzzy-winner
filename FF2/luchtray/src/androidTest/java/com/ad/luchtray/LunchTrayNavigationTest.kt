package com.ad.luchtray

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ad.luchtray.ui.LunchTrayApp
import com.ad.luchtray.ui.LunchTrayRoute
import com.ad.luchtray.ui.stateholder.OrderViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LunchTrayNavigationTest {

   @get:Rule
   val composeTestRule = createAndroidComposeRule<ComponentActivity>()

   private val viewModel = OrderViewModel()
   private lateinit var navController: TestNavHostController

   @Before
   fun setupLunchTrayNavHost() {
      composeTestRule.setContent {
         navController = TestNavHostController(LocalContext.current)
         navController.navigatorProvider.addNavigator(ComposeNavigator())
         LunchTrayApp(navController, viewModel)
      }
   }

   @Test //pass
   fun startApp_verifyStartDestination() {
      navController.assertCurrentRouteName(LunchTrayRoute.Start.name)
   }

   @Test //pass
   fun startApp_verifyBackNavigationNotShownOnStartOrderScreen() {
      val backText = composeTestRule.activity.getString(R.string.back_button)

      composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
   }

   @Test //pass
   fun clickButton_navigateToEntreeMenuScreen() {

      val startOrderText = composeTestRule.activity.getString(R.string.start_order)
      composeTestRule.onNodeWithText(startOrderText)
         .performClick()

      navController.assertCurrentRouteName(LunchTrayRoute.EntreeMenu.name)
   }
}