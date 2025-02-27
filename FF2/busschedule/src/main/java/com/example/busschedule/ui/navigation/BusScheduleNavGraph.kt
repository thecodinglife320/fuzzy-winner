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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.busschedule.R
import com.example.busschedule.ui.FullScheduleViewModel
import com.example.busschedule.ui.schedule.full.FullScheduleDestination
import com.example.busschedule.ui.schedule.full.FullScheduleScreen
import com.example.busschedule.ui.schedule.route.RouteScheduleDestination
import com.example.busschedule.ui.schedule.route.RouteScheduleScreen

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun BusScheduleNavHost(
   navController: NavHostController,
   modifier: Modifier = Modifier,
   viewModel: FullScheduleViewModel = viewModel(
      factory = FullScheduleViewModel.factory
   ), 
) {
   NavHost(
      navController = navController,
      startDestination = FullScheduleDestination.route,
      modifier = modifier
   ) {

      composable(route = FullScheduleDestination.route) {

         viewModel.getFullSchedule()

         FullScheduleScreen(
            onScheduleClick = { busStopName ->
               navController.navigate(
                  "${RouteScheduleDestination.route}/$busStopName"
               )
            },
            topAppBarText = stringResource(R.string.full_schedule),
            navigateUp = { },
            canNavigateBack = false,
            isRouteSchedule = false,
            viewModel = viewModel,
         )
      }

      val routeScheduleArgument = "stop_name"

      composable(
         route = "${RouteScheduleDestination.route}/{$routeScheduleArgument}",
         arguments = listOf(navArgument(routeScheduleArgument) { type = NavType.StringType })
      ) { backStackEntry ->

         val stopName = backStackEntry.arguments?.getString(routeScheduleArgument)
            ?: error("busRouteArgument cannot be null")

         viewModel.getScheduleFor(stopName)

         RouteScheduleScreen(
            navigateUp = { navController.navigateUp() },
            canNavigateBack = true,
            stopName = stopName,
            viewModel = viewModel,
         )
      }
   }
}
