package com.ad.luchtray.ui.stateholder

import com.ad.luchtray.datasource.DataSource
import org.junit.Test

class OrderViewModelTest {

   private val viewModel = OrderViewModel()

   @Test
   fun orderSummary() {

      viewModel.updateEntree(DataSource.entreeMenuItems[0])
      viewModel.updateSideDish(DataSource.sideDishMenuItems[0])
      viewModel.updateAccompaniment(DataSource.accompanimentMenuItems[0])

      print(viewModel.uiStateFlow.value)
   }
}