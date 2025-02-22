package com.ad.amphibians.ui.stateholder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ad.amphibians.AmphibiansApplication
import com.ad.amphibians.data.AmphibiansRepository
import kotlinx.coroutines.launch

class AmphibiansViewModel(
   private val amphibiansRepository: AmphibiansRepository
) : ViewModel() {

   var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
      private set

   init {
      getAmphibians()
   }

   fun getAmphibians() {
      viewModelScope.launch {
         amphibiansUiState = try {

            AmphibiansUiState.Success(amphibiansRepository.getAmphibians())

         } catch (e: Exception) {
            AmphibiansUiState.Error
         }
      }
   }

   companion object {
      val Factory: ViewModelProvider.Factory = viewModelFactory {
         initializer {
            val application = (this[APPLICATION_KEY] as AmphibiansApplication)
            val amphibiansRepository = application.container.amphibiansRepository
            AmphibiansViewModel(amphibiansRepository)
         }
      }
   }
}