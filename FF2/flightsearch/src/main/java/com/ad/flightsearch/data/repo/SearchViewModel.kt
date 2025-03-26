package com.ad.flightsearch.data.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad.flightsearch.data.model.Airport
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
   private val flightRepo: FlightRepo,
) : ViewModel() {

   private val searchUiStateColdFlow =
      flightRepo.airPortFlow.map {
         SearchUiState(
            suggests = it
         )
      }

   val searchUiStateHotFlow = searchUiStateColdFlow
      .stateIn(
         scope = viewModelScope,
         started = SharingStarted.Companion.WhileSubscribed(5_000),
         initialValue = SearchUiState()
      )

   fun searchAirports(query: String) = flightRepo.searchAirports(query)
}

data class SearchUiState(
   val searchText: String = "",
   val suggests: List<Airport> = emptyList(),
)