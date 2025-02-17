package com.ad.cupcake.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ad.cupcake.R

@Composable
private fun ButtonSection(
   modifier: Modifier = Modifier,
   onCancelButtonClicked: () -> Unit,
   onNextButtonClicked: () -> Unit
) {
   Row(modifier = modifier) {

      //Cancel button
      OutlinedButton(
         onClick = onCancelButtonClicked,
         modifier = Modifier.weight(1f),
      ) {
         Text(stringResource(R.string.cancel))
      }

      Spacer(Modifier.width(dimensionResource(R.dimen.padding_medium)))

      //Next button
      Button(
         onClick = onNextButtonClicked,
         modifier = Modifier.weight(1f),
      ) {
         Text(stringResource(R.string.next))
      }
   }
}

@Composable
private fun Flavors_PickUpDate(
   subtotal: String,
   options: List<String>,
   modifier: Modifier = Modifier,
   onSelectionChanged: (String) -> Unit = {}
) {

   var selectedValue by rememberSaveable { mutableStateOf("") }

   Column(modifier = modifier) {

      //Flavor
      Column {
         options.forEach { option ->
            Row(
               verticalAlignment = Alignment.CenterVertically
            ) {
               RadioButton(
                  selected = option == selectedValue,
                  onClick = {
                     selectedValue = option
                     onSelectionChanged(option)
                  },
                  modifier.testTag(option)
               )
               Text(option)
            }
         }
      }

      HorizontalDivider(
         thickness = 2.dp,
         modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
      )

      //Subtotal
      Text(
         style = MaterialTheme.typography.labelLarge,
         text = "Subtotal $subtotal",
         modifier = modifier
            .align(Alignment.End)
            .padding(
               end = dimensionResource(R.dimen.padding_medium),
               top = dimensionResource(R.dimen.padding_medium)
            )
      )
   }
}

@Composable
fun SelectOptionScreen(
   options: List<String>,
   subtotal: String,
   onSelectionChanged: (String) -> Unit,
   onCancelButtonClicked: () -> Unit,
   onNextButtonClicked: () -> Unit,
   modifier: Modifier = Modifier,
) {

   Column(modifier) {
      Flavors_PickUpDate(
         subtotal = subtotal,
         options = options,
         onSelectionChanged = onSelectionChanged
      )
      Spacer(Modifier.height(300.dp))
      ButtonSection(
         onCancelButtonClicked = onCancelButtonClicked,
         onNextButtonClicked = onNextButtonClicked
      )
   }
}