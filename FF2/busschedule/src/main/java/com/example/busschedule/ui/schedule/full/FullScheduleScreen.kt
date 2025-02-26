package com.example.busschedule.ui.schedule.full

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.busschedule.BusScheduleTopAppBar
import com.example.busschedule.R
import com.example.busschedule.data.model.toBusScheduleDetail
import com.example.busschedule.ui.FullScheduleViewModel
import com.example.busschedule.ui.navigation.NavigationDestination

object FullScheduleDestination : NavigationDestination {
   override val route = "full_schedule"
   override val titleRes = R.string.app_name
}

@Composable
fun FullScheduleScreen(
   navigateUp: () -> Unit,
   canNavigateBack: Boolean,
   viewModel: FullScheduleViewModel = viewModel(
      factory = FullScheduleViewModel.factory
   )
) {

   val fullScheduleUiState by viewModel.fullScheduleUiStateFlow.collectAsState()

   Scaffold(
      topBar = {
         BusScheduleTopAppBar(
            title = stringResource(FullScheduleDestination.titleRes),
            canNavigateBack = canNavigateBack,
            navigateUp = navigateUp,
         )
      },
      content = { paddingValues ->
         LazyColumn(
            modifier = Modifier
               .fillMaxSize()
               .padding(paddingValues),
            content = {
               items(items = fullScheduleUiState.scheduleList, key = { it.id }) { schedule ->
                  run {
                     val scheduleDetail = schedule.toBusScheduleDetail()
                     ScheduleItem(
                        stopName = scheduleDetail.stopName,
                        arrivalTime = scheduleDetail.arrivalTimeInMillis,
                        modifier = Modifier
                           .fillMaxWidth()
                           .padding(horizontal = 16.dp, vertical = 8.dp),
                     )
                  }
               }
            }
         )
      }
   )
}

@Composable
fun ScheduleItem(
   stopName: String,
   arrivalTime: String,
   modifier: Modifier = Modifier
) {
   Row(
      modifier = modifier,
      horizontalArrangement = Arrangement.SpaceBetween
   ) {
      Text(
         text = stopName,
         modifier = Modifier.weight(1f)
      )
      Text(
         text = arrivalTime,
         modifier = Modifier.weight(1f),
         textAlign = TextAlign.End
      )
   }
}


@Preview(showBackground = true)
@Composable
fun FullScheduleScreenPreview() {
   //FullScheduleScreen()
}

