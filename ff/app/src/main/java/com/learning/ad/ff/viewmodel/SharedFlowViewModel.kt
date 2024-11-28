package com.learning.ad.ff.viewmodel

import android.util.*
import androidx.lifecycle.*
import com.learning.ad.ff.observer.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class SharedFlowViewModel: ViewModel() {
   private val _sharedFlow = MutableSharedFlow<Int>()
   val sharedFlow = _sharedFlow.asSharedFlow()
   init {
      sharedFlowInit()
   }
   private fun sharedFlowInit() {
      viewModelScope.launch {
         for (i in 1..1000) {
            Log.d(TAG, "Emitting $i")
            delay(2000)
            _sharedFlow.emit(i)
         }
      }
   }

   override fun onCleared() {
      super.onCleared()
      Log.d(TAG, "Destroy")
   }
}