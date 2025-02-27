package com.example.busschedule.ui.schedule.route

import androidx.compose.runtime.Composable
import com.example.busschedule.ui.FullScheduleViewModel
import com.example.busschedule.ui.navigation.NavigationDestination
import com.example.busschedule.ui.schedule.full.FullScheduleScreen

object RouteScheduleDestination : NavigationDestination {
   override val route = "route_schedule"
}

@Composable
fun RouteScheduleScreen(
   canNavigateBack: Boolean,
   navigateUp: () -> Unit,
   stopName: String,
   viewModel: FullScheduleViewModel
) {

   FullScheduleScreen(
      topAppBarText = stopName,
      navigateUp = navigateUp,
      canNavigateBack = canNavigateBack,
      isRouteSchedule = true,
      viewModel = viewModel
   )
}

