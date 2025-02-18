package com.ad.luchtray.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.ad.luchtray.R
import com.ad.luchtray.datasource.DataSource
import com.ad.luchtray.ui.stateholder.OrderViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class EntreeMenuScreenKtTest {

   @get:Rule
   val composeTestRule = createAndroidComposeRule<ComponentActivity>()

   @Test //pass
   fun selectEntree_updateEntreeData() {

      //given
      val options = DataSource.entreeMenuItems
      val viewModel = OrderViewModel()
      val nextButtonText = composeTestRule.activity.getString(R.string.next)

      //when
      composeTestRule.setContent {
         EntreeMenuScreen(
            options = options,
            onCancelButtonClicked = {},
            onNextButtonClicked = {},
            onSelectionChanged = { entreeItem -> viewModel.updateEntree(entreeItem) },
         )
      }

      composeTestRule.onNodeWithText(nextButtonText.uppercase()).assertIsNotEnabled()

      composeTestRule.onNodeWithTag(options[0].name)
         .performClick()

      //then
      val expectedEntree = options[0]
      assertEquals(expectedEntree, viewModel.uiStateFlow.value.entree)
      composeTestRule.onNodeWithText(nextButtonText.uppercase()).assertIsEnabled()
   }
}