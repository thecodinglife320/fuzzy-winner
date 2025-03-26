package com.ad.restaurant.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun RestaurantIcon(
   modifier: Modifier = Modifier,
   icon: ImageVector = Icons.Filled.Place,
   onClick: () -> Unit = {},
) {
   Image(
      imageVector = icon,
      contentDescription = "Restaurant icon",
      modifier = modifier
         .clickable {
            onClick()
         }
   )
}