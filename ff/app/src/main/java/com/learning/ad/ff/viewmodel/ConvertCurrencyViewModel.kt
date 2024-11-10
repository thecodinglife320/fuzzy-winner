package com.learning.ad.ff.viewmodel

import androidx.lifecycle.ViewModel

class ConvertCurrencyViewModel:ViewModel() {
   private val rate = 0.74f
   var result= 0.0f

   fun convert(dollar:String): Float {
      result = dollar.toFloat()*rate
      return result
   }
}