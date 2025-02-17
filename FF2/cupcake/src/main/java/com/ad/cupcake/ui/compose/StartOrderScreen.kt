package com.ad.cupcake.ui.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ad.cupcake.R
import com.ad.cupcake.data.DataSource

@Composable
private fun ImageWithCaption(
   modifier: Modifier = Modifier
) {
   Column(
      modifier = modifier,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Image(
         painter = painterResource(id = R.drawable.cupcake),
         contentDescription = null,
      )
      Text(
         style = MaterialTheme.typography.headlineSmall,
         text = stringResource(R.string.image_caption)
      )
   }
}

@Composable
fun StartOrderScreen(
   quantityOptions: List<Pair<Int, Int>>,
   onNextButtonClicked: (Int) -> Unit,
   modifier: Modifier = Modifier
) {

   Column(
      modifier = modifier,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {

      //image with caption
      ImageWithCaption()

      Spacer(Modifier.height(200.dp))

      //button section
      Column {
         quantityOptions.forEach { item ->
            SelectQuantityButton(
               labelResourceId = item.first,
               onClick = { onNextButtonClicked(item.second) }
            )
         }
      }
   }
}

@Composable
private fun SelectQuantityButton(
   @StringRes labelResourceId: Int,
   onClick: () -> Unit,
   modifier: Modifier = Modifier
) {
   Button(
      onClick = onClick,
      modifier = modifier.widthIn(min = 250.dp)
   ) {
      Text(stringResource(labelResourceId))
   }
}

@Preview(showSystemUi = true, apiLevel = 31, device = "id:4.7in WXGA")
@Composable
private fun ImageWithCaptionPreview() {
   StartOrderScreen(
      DataSource.quantityOptions,
      onNextButtonClicked = {},
      Modifier.fillMaxSize()
   )
}