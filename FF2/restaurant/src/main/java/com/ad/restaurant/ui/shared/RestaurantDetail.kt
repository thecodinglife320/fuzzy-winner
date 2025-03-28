package com.ad.restaurant.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun RestaurantDetails(
   modifier: Modifier = Modifier,
   title: String = "anh duong",
   description: String = "Ngon bo re",
   horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {
   Column(
      modifier = modifier,
      horizontalAlignment = horizontalAlignment
   ) {
      Text(
         text = title,
         style = MaterialTheme.typography.headlineSmall
      )
      Text(
         text = description,
         style = MaterialTheme.typography.bodySmall,
      )
   }
}