package com.ad.ff2.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
   small = RoundedCornerShape(50.dp),
   medium = RoundedCornerShape(
      topStart = 15.dp,
      bottomStart = 50.dp,
      topEnd = 50.dp,
      bottomEnd = 15.dp,
   )
)

val ShapesHero = Shapes(
   small = RoundedCornerShape(8.dp),
   medium = RoundedCornerShape(16.dp),
   large = RoundedCornerShape(16.dp)
)