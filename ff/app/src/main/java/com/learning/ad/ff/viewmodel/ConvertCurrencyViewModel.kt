package com.learning.ad.ff.viewmodel

import androidx.lifecycle.*

class ConvertCurrencyViewModel : ViewModel() {
   private val rate = 0.74f
   private val _result = MutableLiveData(0f)
   val result: LiveData<Float> get() = _result

   fun convert(dollar: String) {
      _result.value = dollar.toFloat() * rate
   }
}