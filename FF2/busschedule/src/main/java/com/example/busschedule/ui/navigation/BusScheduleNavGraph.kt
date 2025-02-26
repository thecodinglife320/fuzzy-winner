/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.busschedule.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.busschedule.ui.schedule.full.FullScheduleDestination
import com.example.busschedule.ui.schedule.full.FullScheduleScreen

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun BusScheduleNavHost(
   navController: NavHostController,
   modifier: Modifier = Modifier,
) {
   NavHost(
      navController = navController,
      startDestination = FullScheduleDestination.route,
      modifier = modifier
   ) {
      composable(route = FullScheduleDestination.route) {
         FullScheduleScreen(
            navigateUp = { },
            canNavigateBack = false
         )
      }
   }
}
