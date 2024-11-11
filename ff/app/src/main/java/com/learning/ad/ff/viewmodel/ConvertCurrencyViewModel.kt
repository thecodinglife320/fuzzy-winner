package com.learning.ad.ff.viewmodel

import androidx.lifecycle.*
const val RESULT_KEY = "Euro value"
class ConvertCurrencyViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
   private val rate = 0.74f
   var dollarValue = MutableLiveData<String>()
   var result= MutableLiveData<Float>(savedStateHandle[RESULT_KEY])
   fun convertValue() {
      result.value = (if (dollarValue.value.isNullOrEmpty()) 0f else dollarValue.value!!.toFloat() * rate)
      dollarValue.value=""
      savedStateHandle[RESULT_KEY] = result.value
   }
}