/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication
import com.example.busschedule.data.BusScheduleDao
import com.example.busschedule.data.model.BusSchedule
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FullScheduleViewModel(
   private val scheduleDao: BusScheduleDao
) : ViewModel() {

   lateinit var fullScheduleUiStateFlow: StateFlow<FullScheduleUiState>

   fun getFullSchedule() {
      fullScheduleUiStateFlow = scheduleDao.getFullSchedule()
         .map {
            FullScheduleUiState(it)
         }
         .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = FullScheduleUiState()
         )
   }

   fun getScheduleFor(stopName: String) {
      fullScheduleUiStateFlow =
         scheduleDao.getScheduleFor(stopName)
            .map {
               FullScheduleUiState(it)
            }
            .stateIn(
               scope = viewModelScope,
               started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
               initialValue = FullScheduleUiState()
            )
   }

   companion object {
      val factory: ViewModelProvider.Factory = viewModelFactory {
         initializer {
            FullScheduleViewModel(
               scheduleDao = busScheduleApplication().container.busScheduleDao
            )
         }
      }
      private const val TIMEOUT_MILLIS = 5_000L
   }

}

fun CreationExtras.busScheduleApplication(): BusScheduleApplication =
   (this[AndroidViewModelFactory.APPLICATION_KEY] as BusScheduleApplication)

/**
 * Ui State for FullScheduleScreen
 */
data class FullScheduleUiState(val scheduleList: List<BusSchedule> = listOf())

