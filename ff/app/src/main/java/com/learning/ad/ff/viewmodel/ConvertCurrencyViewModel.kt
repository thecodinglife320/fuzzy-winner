package com.learning.ad.ff.viewmodel

import androidx.lifecycle.*

class ConvertCurrencyViewModel : ViewModel() {
   private val rate = 0.74f
   var dollarValue = MutableLiveData<String>()
   var result = MutableLiveData<Float>()
   fun convertValue() {
      result.value = (if (dollarValue.value.isNullOrEmpty()) 0f else dollarValue.value!!.toFloat() * rate)
      dollarValue.value=""
   }
}