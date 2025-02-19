package com.ad.luchtray

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ad.luchtray.datasource.DataSource
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

   //next navigation test case section
   @Test //pass
   fun startApp_verifyStartDestination() {
      navController.assertCurrentRouteName(LunchTrayRoute.Start.name)
   }

   @Test //pass
   fun startOrderScreen_clickStartOrderButton_navigateToEntreeMenuScreen() {

      navigateToEntreeMenu()

      navController.assertCurrentRouteName(LunchTrayRoute.EntreeMenu.name)
   }

   @Test //pass
   fun entreeMenuScreen_clickNextButton_navigateToSideDishMenuScreen() {

      navigateToEntreeMenu()

      navigateToSideDishMenu()

      navController.assertCurrentRouteName(LunchTrayRoute.SideDishMenu.name)
   }

   @Test //pass
   fun sideDishMenuScreen_clickNextButton_navigateToAccompanimentMenuScreen() {
      navigateToEntreeMenu()
      navigateToSideDishMenu()
      navigateToAccompanimentMenu()
      navController.assertCurrentRouteName(LunchTrayRoute.AccompanimentMenu.name)
   }

   //back navigation test case section
   @Test //pass
   fun startApp_verifyBackNavigationNotShownOnStartOrderScreen() {
      val backText = composeTestRule.activity.getString(R.string.back_button)

      composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
   }

   @Test //pass
   fun entreeMenuScreen_clickBackButton_backToStartOrderScreen() {

      navigateToEntreeMenu()

      clickBackButton()

      navController.assertCurrentRouteName(LunchTrayRoute.Start.name)

   }

   @Test //pass
   fun sideDishMenuScreen_clickBackButton_navigateToEntreeMenuScreen() {
      navigateToEntreeMenu()
      navigateToSideDishMenu()
      clickBackButton()
      navController.assertCurrentRouteName(LunchTrayRoute.EntreeMenu.name)
   }

   @Test //pass
   fun accompanimentMenuScreen_clickBackButton_navigateToSideDishMenuScreen() {
      navigateToEntreeMenu()
      navigateToSideDishMenu()
      navigateToAccompanimentMenu()
      clickBackButton()
      navController.assertCurrentRouteName(LunchTrayRoute.SideDishMenu.name)
   }

   //cancel order test case section
   @Test //pass
   fun cancel_navigatesToStartOrder() {
      navigateToEntreeMenu()
      clickCancelButton()
      navController.assertCurrentRouteName(LunchTrayRoute.Start.name)
   }

   //helper functions
   private fun navigateToEntreeMenu() {
      composeTestRule.onNodeWithStringId(R.string.start_order)
         .performClick()
   }

   private fun navigateToSideDishMenu() {

      composeTestRule.onNodeWithTag(DataSource.entreeMenuItems[0].name)
         .performClick()

      clickNextButton()
   }

   private fun navigateToAccompanimentMenu() {
      composeTestRule.onNodeWithTag(DataSource.sideDishMenuItems[0].name)
         .performClick()

      clickNextButton()
   }

   private fun clickCancelButton() {
      composeTestRule.onNodeWithStringId(R.string.cancel)
         .performScrollTo()
         .performClick()
   }

   private fun clickNextButton() {
      composeTestRule.onNodeWithStringId(R.string.next)
         .performScrollTo()
         .performClick()
   }

   private fun clickBackButton() {
      val backText = composeTestRule.activity.getString(R.string.back_button)
      composeTestRule.onNodeWithContentDescription(backText)
         .performClick()
   }
}