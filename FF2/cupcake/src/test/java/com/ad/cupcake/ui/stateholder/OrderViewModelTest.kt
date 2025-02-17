package com.ad.cupcake.ui.stateholder

import org.junit.Assert.assertEquals
import org.junit.Test

class OrderViewModelTest {

   private val viewModel = OrderViewModel()

   @Test
   fun start() {
      println(viewModel.uiStateFlow.value)
   }

   @Test
   fun setQuantity_calculatePrice() {

      //given
      viewModel.setQuantity(5)

      //when

      //then
      assertEquals("$10.00", viewModel.uiStateFlow.value.price)
   }

   @Test
   fun setSameDate_calculatePrice() {

      //given
      viewModel.setQuantity(5)
      viewModel.setDate(viewModel.uiStateFlow.value.pickupOptions[0])

      //when

      //then
      assertEquals("$13.00", viewModel.uiStateFlow.value.price)
   }

   @Test
   fun setDiffDate_calculatePrice() {

      //given
      viewModel.setQuantity(5)

      repeat(3) {
         viewModel.setDate(viewModel.uiStateFlow.value.pickupOptions[it + 1])

         //then
         assertEquals("$10.00", viewModel.uiStateFlow.value.price)
      }
   }
}