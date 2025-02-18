package com.ad.luchtray.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.ad.luchtray.R
import com.ad.luchtray.datasource.DataSource
import com.ad.luchtray.model.MenuItem
import com.ad.luchtray.model.MenuItem.SideDishItem

@Composable
fun SideDishMenuScreen(
   options: List<SideDishItem>,
   onCancelButtonClicked: () -> Unit,
   onNextButtonClicked: () -> Unit,
   onSelectionChanged: (SideDishItem) -> Unit,
   modifier: Modifier = Modifier
) {
   BaseMenuScreen(
      options = options,
      onCancelButtonClicked = onCancelButtonClicked,
      onNextButtonClicked = onNextButtonClicked,
      onSelectionChanged = onSelectionChanged as (MenuItem) -> Unit,
      modifier = modifier
   )
}

@Preview
@Composable
private fun SideDishMenuPreview() {
   SideDishMenuScreen(
      options = DataSource.sideDishMenuItems,
      onNextButtonClicked = {},
      onCancelButtonClicked = {},
      onSelectionChanged = {},
      modifier = Modifier
         .padding(dimensionResource(R.dimen.padding_medium))
         .verticalScroll(rememberScrollState())
   )
}
