package com.ad.dessertshop.ui.uistate

import com.ad.dessertshop.model.Dessert

data class ShopUiState(
   val revenue: Int = 0,
   val dessertsSold: Int = 0,
   val currentDessert: Dessert? = null,
)
