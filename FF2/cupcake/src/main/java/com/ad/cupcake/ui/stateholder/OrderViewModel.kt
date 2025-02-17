package com.ad.cupcake.ui.stateholder

import androidx.lifecycle.ViewModel
import com.ad.cupcake.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OrderViewModel : ViewModel() {

   companion object {
      private const val PRICE_PER_CUPCAKE = 2.00
      private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00
   }

   private val _uiStateFlow = MutableStateFlow(OrderUiState(pickupOptions = pickupOptions()))
   val uiStateFlow: StateFlow<OrderUiState> = _uiStateFlow.asStateFlow()

   private fun pickupOptions(): List<String> {
      val dateOptions = mutableListOf<String>()
      val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
      val calendar = Calendar.getInstance()
      // add current date and the following 3 dates.
      repeat(4) {
         dateOptions.add(formatter.format(calendar.time))
         calendar.add(Calendar.DATE, 1)
      }
      return dateOptions
   }

   private fun calculatePrice(
      quantity: Int = _uiStateFlow.value.quantity,
      pickupDate: String = _uiStateFlow.value.date
   ): String {

      var calculatedPrice = quantity * PRICE_PER_CUPCAKE
      // If the user selected the first option (today) for pickup, add the surcharge

      if (pickupOptions()[0] == pickupDate) {
         calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
      }

      val formattedPrice = NumberFormat.getCurrencyInstance(Locale.US).format(calculatedPrice)

      return formattedPrice
   }

   fun resetOrder() {
      _uiStateFlow.value = OrderUiState(pickupOptions = pickupOptions())
   }

   fun setDate(pickupDate: String) {
      _uiStateFlow.update { currentState ->
         currentState.copy(
            date = pickupDate,
            price = calculatePrice(pickupDate = pickupDate)
         )
      }
   }

   fun setFlavor(desiredFlavor: String) {
      _uiStateFlow.update { currentState ->
         currentState.copy(flavor = desiredFlavor)
      }
   }

   fun setQuantity(numberCupcakes: Int) {
      _uiStateFlow.update { currentState ->
         currentState.copy(
            quantity = numberCupcakes,
            price = calculatePrice(quantity = numberCupcakes)
         )
      }
   }
}