package com.ad.amphibians.ui.stateholder

import com.ad.amphibians.model.Amphibians

sealed interface AmphibiansUiState {
   data class Success(val amphibians: List<Amphibians>) : AmphibiansUiState
   data object Error : AmphibiansUiState
   data object Loading : AmphibiansUiState

}