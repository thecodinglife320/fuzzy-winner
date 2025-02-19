/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ad.luchtray.ui.stateholder

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ad.luchtray.model.MenuItem
import com.ad.luchtray.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.util.Locale

class OrderViewModel : ViewModel() {

   private val taxRate = 0.08

   private val _uiStateFlow = MutableStateFlow(OrderUiState())
   val uiStateFlow: StateFlow<OrderUiState> = _uiStateFlow.asStateFlow()

   fun updateEntree(entree: MenuItem.EntreeItem) {
      val previousEntree = _uiStateFlow.value.entree
      updateItem(entree, previousEntree)
   }

   fun updateSideDish(sideDish: MenuItem.SideDishItem) {
      val previousSideDish = _uiStateFlow.value.sideDish
      updateItem(sideDish, previousSideDish)
   }

   fun updateAccompaniment(accompaniment: MenuItem.AccompanimentItem) {
      val previousAccompaniment = _uiStateFlow.value.accompaniment
      updateItem(accompaniment, previousAccompaniment)
   }

   fun resetOrder() {
      _uiStateFlow.value = OrderUiState()
   }

   private fun updateItem(newItem: MenuItem, previousItem: MenuItem?) {
      _uiStateFlow.update { currentState ->
         val previousItemPrice = previousItem?.price ?: 0.0
         // subtract previous item price in case an item of this category was already added.
         val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
         // recalculate tax
         val tax = itemTotalPrice * taxRate
         currentState.copy(
            itemTotalPrice = itemTotalPrice,
            orderTax = tax,
            orderTotalPrice = itemTotalPrice + tax,
            entree = if (newItem is MenuItem.EntreeItem) newItem else currentState.entree,
            sideDish = if (newItem is MenuItem.SideDishItem) newItem else currentState.sideDish,
            accompaniment =
            if (newItem is MenuItem.AccompanimentItem) newItem else currentState.accompaniment
         )
      }
   }

   fun saveOrderToFile(context: Context) {
      val order = _uiStateFlow.value
      val stringBuilder = StringBuilder()

      stringBuilder.apply {
         appendLine("Order Summary")
         appendLine("Entree: ${order.entree?.name}")
         appendLine("Side Dish: ${order.sideDish?.name}")
         appendLine("Accompaniment: ${order.accompaniment?.name}")
         appendLine("Total: ${order.orderTotalPrice.formatPrice()}")
         appendLine("*********************************************")
      }

      context.openFileOutput("order.txt", Context.MODE_APPEND).use { output ->
         output.write(stringBuilder.toString().toByteArray())
      }
   }
}

fun Double.formatPrice(): String {
   return NumberFormat.getCurrencyInstance(Locale.US).format(this)
}
