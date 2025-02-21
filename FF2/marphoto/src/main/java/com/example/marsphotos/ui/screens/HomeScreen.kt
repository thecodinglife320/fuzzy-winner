/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marsphotos.R
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.stateholder.MarsUiState

@Composable
fun HomeScreen(
   marsUiState: MarsUiState,
   modifier: Modifier = Modifier,
   retryAction: () -> Unit
) {
   when (marsUiState) {
      is MarsUiState.Success ->
         PhotosGridScreen(marsUiState.photo, modifier)

      is MarsUiState.Loading -> LoadingScreen(modifier)
      is MarsUiState.Error -> ErrorScreen(modifier, retryAction)
   }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, retryAction: () -> Unit) {
   Column(
      modifier = modifier,
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Image(
         painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
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
fun MarsPhotoCard(photo: MarsPhoto, modifier: Modifier = Modifier) {
   AsyncImage(
      modifier = modifier,
      model = photo.img_src,
      error = painterResource(R.drawable.ic_broken_image),
      placeholder = painterResource(R.drawable.loading_img),
      contentDescription = stringResource(R.string.mars_photo),
      contentScale = ContentScale.Crop,
   )
}

@Composable
fun PhotosGridScreen(
   photos: List<MarsPhoto>,
   modifier: Modifier = Modifier,
) {
   LazyVerticalGrid(
      columns = GridCells.Adaptive(150.dp),
      modifier = modifier,
   ) {
      items(items = photos, key = { photos -> photos.id }) { photo ->
         MarsPhotoCard(
            photo,
            Modifier
               .aspectRatio(1.5f)
               .fillMaxWidth()
               .padding(4.dp)
         )
      }
   }
}
