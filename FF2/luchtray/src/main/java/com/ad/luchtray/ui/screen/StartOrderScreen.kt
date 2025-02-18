package com.ad.luchtray.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ad.luchtray.R
import com.ad.luchtray.ui.theme.AppTheme

@Composable
fun StartOrderScreen(
   onStartOrderButtonClicked: () -> Unit,
   modifier: Modifier = Modifier
) {
   Column(
      modifier = modifier,
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
   ) {
      Button(
         onClick = onStartOrderButtonClicked,
         Modifier.widthIn(min = 250.dp)
      ) {
         Text(stringResource(R.string.start_order))
      }
   }
}

@Preview(device = "id:4.7in WXGA", showSystemUi = true)
@Composable
fun StartOrderPreview() {
   AppTheme(darkTheme = true, dynamicColor = false) {
      StartOrderScreen(
         onStartOrderButtonClicked = {},
         modifier = Modifier
             .padding(dimensionResource(R.dimen.padding_medium))
             .fillMaxSize()
      )
   }
}
