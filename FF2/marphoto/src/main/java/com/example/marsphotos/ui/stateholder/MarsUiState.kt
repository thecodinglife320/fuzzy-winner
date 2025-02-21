package com.example.marsphotos.ui.stateholder

import com.example.marsphotos.model.MarsPhoto

sealed interface MarsUiState {
   data class Success(val photo: List<MarsPhoto>) : MarsUiState
   data object Error : MarsUiState
   data object Loading : MarsUiState
}