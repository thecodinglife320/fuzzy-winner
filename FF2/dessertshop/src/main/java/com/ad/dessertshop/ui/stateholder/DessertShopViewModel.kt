package com.ad.dessertshop.ui.stateholder

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.ad.dessertshop.R
import com.ad.dessertshop.data.Datasource.dessertList
import com.ad.dessertshop.ui.uistate.ShopUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertShopViewModel : ViewModel() {

   // Dessert shop UI state
   private val _uiStateFlow = MutableStateFlow(ShopUiState())

   val uiStateFlow: StateFlow<ShopUiState>
      get() = _uiStateFlow.asStateFlow()

   init {
      determineDessertToShow()
   }

   private fun determineDessertToShow() {

      var dessertToShow = dessertList.first()

      for (dessert in dessertList) {
         if (uiStateFlow.value.dessertsSold >= dessert.startProductionAmount) dessertToShow =
            dessert
         else break
      }

      _uiStateFlow.update { shopUiState ->
         shopUiState.copy(
            currentDessert = dessertToShow
         )
      }
   }

   fun soldDessert() {

      _uiStateFlow.update { shopUiState ->
         shopUiState.copy(
            revenue = shopUiState.revenue + shopUiState.currentDessert?.price!!,
            dessertsSold = shopUiState.dessertsSold + 1,
         )
      }

      determineDessertToShow()
   }

   fun shareSoldDessertsInformation(intentContext: Context) {
      val sendIntent = Intent().apply {
         action = Intent.ACTION_SEND
         putExtra(
            Intent.EXTRA_TEXT,
            intentContext.getString(
               R.string.share_text,
               _uiStateFlow.value.dessertsSold,
               _uiStateFlow.value.revenue
            )
         )
         type = "text/plain"
      }

      val shareIntent = Intent.createChooser(sendIntent, null)

      try {
         ContextCompat.startActivity(intentContext, shareIntent, null)
      } catch (e: ActivityNotFoundException) {
         Toast.makeText(
            intentContext,
            intentContext.getString(R.string.sharing_not_available),
            Toast.LENGTH_LONG
         ).show()
      }
   }
}