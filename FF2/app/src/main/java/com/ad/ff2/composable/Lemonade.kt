package com.ad.ff2.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ad.ff2.R
import com.ad.ff2.composable.Lemonade.contentDescriptionResourceIds
import com.ad.ff2.composable.Lemonade.imageResourceIds
import com.ad.ff2.composable.Lemonade.squeezeCount
import com.ad.ff2.composable.Lemonade.stringResourceIds

object Lemonade {
   val imageResourceIds = arrayOf(
      R.drawable.lemon_tree,
      R.drawable.lemon_squeeze,
      R.drawable.lemon_drink,
      R.drawable.lemon_restart
   )
   val stringResourceIds = arrayOf(
      R.string.tap_lemon_tree,
      R.string.keep_tapping_lemon,
      R.string.tap_lemonade,
      R.string.tap_empty_glass
   )
   var contentDescriptionResourceIds = arrayOf(
      R.string.lemon_tree,
      R.string.lemon,
      R.string.glass_of_lemonade,
      R.string.empty_glass
   )
   var squeezeCount = 0
}

@Composable
fun LemonadeWithTextAndImage(
   modifier: Modifier = Modifier
) {
   var step by remember { mutableIntStateOf(0) }

   if (step == 1) squeezeCount = (2..4).random()

   Column(
      modifier = modifier,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Text(
         text = "Lemon tree",
         fontSize = 24.sp,
         fontWeight = FontWeight.Bold,
         style = TextStyle(
            brush = Brush.linearGradient(
               colors = listOf(Color(0xFFFFC107), Color(0xFFE84919)) // Vàng -> Cam
            )
         ),
         modifier = Modifier
            .background(Color(0xFFAED581), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 4.dp)
      )
      Spacer(modifier = Modifier.height(16.dp))
      Image(
         modifier = Modifier
            .clickable {
               if (step != 1) {
                  step++
                  if (step == 4) step = 0
               } else {
                  squeezeCount--
                  if (squeezeCount == 0) step = 2
               }
            }
            .background(Color(0xFFB2EBF2), shape = RoundedCornerShape(20.dp))
            .border(2.dp, Color.Green, RoundedCornerShape(20.dp))
            .shadow(5.dp, RoundedCornerShape(20.dp), spotColor = Color.Green),
         painter = painterResource(imageResourceIds[step]),
         contentDescription = stringResource(contentDescriptionResourceIds[step])
      )

      Spacer(modifier = Modifier.height(16.dp))

      Text(
         stringResource(stringResourceIds[step], squeezeCount),
         fontSize = 18.sp
      )
   }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewLemonadeWithTextAndImage() {
   LemonadeWithTextAndImage(
      modifier = Modifier
         .fillMaxSize()
         .wrapContentSize(Alignment.Center)
   )
}