package com.ad.cupcake

import android.annotation.SuppressLint
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ad.cupcake.data.DataSource
import com.ad.cupcake.ui.compose.SelectOptionScreen
import com.ad.cupcake.ui.compose.StartOrderScreen
import com.ad.cupcake.ui.stateholder.OrderViewModel
import com.ad.cupcake.ui.theme.FF2Theme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

   @get:Rule
   val composeTestRule = createComposeRule()

   private val viewModel = OrderViewModel()

   @Test
   fun click_button() {
      composeTestRule.setContent {
         FF2Theme {
            StartOrderScreen(DataSource.quantityOptions, orderViewModel = viewModel)
         }
      }

      composeTestRule.onNodeWithText("Twelve Cupcakes")
         .performClick()

      val expectedPrice = "$24.00"
      assertEquals(expectedPrice, viewModel.uiStateFlow.value.price)
   }

   @SuppressLint("StateFlowValueCalledInComposition")
   @Test
   fun setPickUpDate() {
      composeTestRule.setContent {
         FF2Theme {
            SelectOptionScreen(
               options = viewModel.uiStateFlow.value.pickupOptions,
               viewModel = viewModel,
               subtotal = uiState.price,
               onSelectionChanged = { viewModel.setFlavor(it) }
            )
         }
      }

      composeTestRule.onNodeWithTag(viewModel.uiStateFlow.value.pickupOptions[1])
         .performClick()

      val expectedDate = viewModel.uiStateFlow.value.pickupOptions[0]
      val expectedSurCharge = "$3.00"
      assertEquals(expectedSurCharge, viewModel.uiStateFlow.value.price)
   }
}