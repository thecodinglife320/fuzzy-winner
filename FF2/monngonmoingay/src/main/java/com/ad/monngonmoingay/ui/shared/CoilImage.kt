package com.ad.monngonmoingay.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.ad.monngonmoingay.R

@Composable
fun CoilImage(
   imageUrl: String,
   modifier: Modifier = Modifier
) {
   AsyncImage(
      model = imageUrl,
      error = painterResource(R.drawable.ic_broken_image1),
      placeholder = painterResource(R.drawable.loading_img),
      contentDescription = null,
      contentScale = ContentScale.FillBounds,
      modifier = modifier
   )
}