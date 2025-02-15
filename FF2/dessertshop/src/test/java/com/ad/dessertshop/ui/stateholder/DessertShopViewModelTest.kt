package com.ad.dessertshop.ui.stateholder

import com.ad.dessertshop.data.Datasource.dessertList
import org.junit.Assert.assertEquals
import org.junit.Test

class DessertShopViewModelTest {

   private val viewModel = DessertShopViewModel()

   @Test
   fun dessertShopViewModel_firstDessertSold_revenueAndDessertSoldUpdated() {

      viewModel.soldDessert()
      assertEquals(FIRST_DESSERT_SOLD, viewModel.uiStateFlow.value.dessertsSold)
      assertEquals(FIRST_REVENUE, viewModel.uiStateFlow.value.revenue)
   }

   @Test
   fun dessertShopViewModel_start() {

      assertEquals(START_REVENUE, viewModel.uiStateFlow.value.revenue)
      assertEquals(START_DESSERT_SOLD, viewModel.uiStateFlow.value.dessertsSold)
      assertEquals(dessertList.first(), viewModel.uiStateFlow.value.currentDessert)
   }

   @Test
   fun dessertShopViewModel_determineDessertToShow_SecondDessert() {

      //given
      repeat(21) {
         viewModel.soldDessert()
      }
      val expectedDessert = dessertList[2]

      //when

      //then
      assertEquals(expectedDessert, viewModel.uiStateFlow.value.currentDessert)
   }

   companion object {
      private const val START_REVENUE = 0
      private const val START_DESSERT_SOLD = 0

      private const val FIRST_REVENUE = 5
      private const val FIRST_DESSERT_SOLD = 1
   }
}