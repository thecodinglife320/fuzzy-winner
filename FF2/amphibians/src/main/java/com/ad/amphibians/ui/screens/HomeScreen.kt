package com.ad.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ad.amphibians.R
import com.ad.amphibians.model.Amphibians
import com.ad.amphibians.ui.stateholder.AmphibiansUiState
import com.ad.amphibians.ui.theme.AppTheme

@Composable
fun HomeScreen(
   amphibiansUiState: AmphibiansUiState,
   modifier: Modifier = Modifier,
   retryAction: () -> Unit
) {
   when (amphibiansUiState) {
      is AmphibiansUiState.Success ->
         AmphibiansScreen(amphibiansUiState.amphibians, modifier)

      is AmphibiansUiState.Loading -> LoadingScreen(modifier)
      is AmphibiansUiState.Error -> ErrorScreen(retryAction, modifier)
   }
}

@Composable
fun AmphibiansScreen(amphibians: List<Amphibians>, modifier: Modifier = Modifier) {
   LazyColumn(modifier = modifier) {
      items(amphibians) {
         AmphibiansCard(
            amphibian = it,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
         )
      }
   }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
   Column(
      modifier = modifier,
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Image(
         painter = painterResource(id = R.drawable.ic_connection_error),
         contentDescription = stringResource(R.string.loading_failed)
      )
      Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
      Button(onClick = retryAction) {
         Text(stringResource(R.string.retry))
      }
   }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
   Image(
      modifier = modifier.size(200.dp),
      painter = painterResource(R.drawable.loading_img),
      contentDescription = stringResource(R.string.loading)
   )
}

@Composable
fun AmphibiansCard(amphibian: Amphibians, modifier: Modifier = Modifier) {
   Card(modifier) {
      Column() {
         Text(
            text = "${amphibian.name} (${amphibian.type})",
            //style = MaterialTheme.typography.titleLarge
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
         )
         AsyncImage(
            model = amphibian.img_src,
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.amphibians_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier
               .aspectRatio(1.5f)
               .fillMaxWidth()
         )
         Text(
            textAlign = TextAlign.Justify,
            text = amphibian.description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
         )
      }
   }
}

@Preview
@Composable
fun Preview() {
   val amphibians = List(3) {
      Amphibians(
         name = "a",
         type = "v",
         description = "aaaaaaaaaaaaaaaaaa",
         img_src = "a"
      )
   }
   AppTheme {
      AmphibiansScreen(amphibians)
   }
}
