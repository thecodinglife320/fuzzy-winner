package com.learning.ad.ff.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.flow.*

class MainViewModel: ViewModel() {
   private val _userInput = MutableStateFlow("")
   val userInput = _userInput.asStateFlow()
   fun updateUserInput(input:String){
      _userInput.value = input
   }
}