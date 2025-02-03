package com.ad.ff2.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ad.ff2.R

object ArtSpace {
   val imageResourceIds = arrayOf(
      R.drawable.tranh1,
      R.drawable.tranh2,
      R.drawable.tranh3,
      R.drawable.tranh4
   )
   val imageNameIds = arrayOf(
      "Emotional Life",
      "Simple Life",
      "Creative Life",
      "Creative Life"
   )
   val artistNameIds = arrayOf(
      "ttll(2025)",
      "ttll(2025)",
      "ttll(2025)",
      "ttll(2025)"
   )
}

@Composable
fun ArtSpaceLayout() {

   // Screen UI state
   var step by remember { mutableIntStateOf(0) }

   //UI element state
   val image = painterResource(ArtSpace.imageResourceIds[step])
   val imageName = ArtSpace.imageNameIds[step]
   val artistName = ArtSpace.artistNameIds[step]

   Column(
      modifier = Modifier
         .statusBarsPadding()
         .padding(horizontal = 20.dp)
         .safeDrawingPadding()
         .verticalScroll(rememberScrollState()),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
   ) {
      ArtSpaceImage(painter = image)
      ArtSpaceDescription(
         imageName = imageName,
         artistName = artistName,
         Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.Start)
            .padding(top = 16.dp, start = 16.dp)
      )
      ArtSpaceButtons(
         previous = {
            step--
            if (step == -1) step = ArtSpace.imageResourceIds.size - 1
         },
         next = {
            step++
            if (step == ArtSpace.imageResourceIds.size) step = 0
         },
         Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
      )
   }
}

@Composable
private fun ArtSpaceImage(painter: Painter, modifier: Modifier = Modifier) {
   Surface(shadowElevation = 24.dp) {
      Image(
         painter = painter,
         contentDescription = null,
         modifier = modifier
            .fillMaxWidth(),
         contentScale = ContentScale.FillWidth
      )
   }
}

@Composable
private fun ArtSpaceButtons(
   previous: () -> Unit,
   next: () -> Unit,
   modifier: Modifier = Modifier
) {
   Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
      Button(onClick = previous) {
         Text(text = "Previous")
      }
      Button(onClick = next) {
         Text(text = "Next")
      }
   }
}

@Composable
private fun ArtSpaceDescription(
   imageName: String,
   artistName: String,
   modifier: Modifier = Modifier
) {
   Text(
      buildAnnotatedString {
         withStyle(
            style = SpanStyle(
               color = Color.Red,
               fontWeight = FontWeight.Bold,
               fontSize = 20.sp
            )
         ) {
            append("$imageName\n")
         }
         withStyle(style = SpanStyle(color = Color.Blue, fontStyle = FontStyle.Italic)) {
            append(artistName)
         }
      },
      modifier = modifier
   )
}

@Preview(showBackground = true, showSystemUi = true, device = "id:4.65in 720p (Galaxy Nexus)")
@Composable
fun ArtSpaceLayoutPreview() {
   ArtSpaceLayout()
}