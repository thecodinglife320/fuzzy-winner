package com.ad.ff2.woof

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ad.ff2.R
import com.ad.ff2.ui.theme.WoofTheme

@Composable
fun WoofApp() {
   Scaffold(
      topBar = {
         WoofTopAppBar()
      }
   ) { it ->
      LazyColumn(contentPadding = it) {
         items(dogs) {
            DogItem(
               dog = it,
               modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
         }
      }
   }
}

@Composable
fun DogItem(
   dog: Dog,
   modifier: Modifier = Modifier
) {
   Card(modifier) {
      Row(
         modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small))
      ) {
         DogIcon(dog.imageResourceId)
         DogInformation(dog.name, dog.age)
      }
   }
}

@Composable
fun DogIcon(
   @DrawableRes dogIcon: Int,
   modifier: Modifier = Modifier
) {
   Image(
      modifier = modifier
         .size(dimensionResource(R.dimen.image_size))
         .padding(dimensionResource(R.dimen.padding_small))
         .clip(MaterialTheme.shapes.small),
      painter = painterResource(dogIcon),

      // Content Description is not needed here - image is decorative, and setting a null content
      // description allows accessibility services to skip this element during navigation.

      contentDescription = null,
      contentScale = ContentScale.Crop
   )
}

@Composable
fun DogInformation(
   @StringRes dogName: Int,
   dogAge: Int,
   modifier: Modifier = Modifier
) {
   Column(modifier = modifier) {
      Text(
         text = stringResource(dogName),
         style = MaterialTheme.typography.displayMedium,
         modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
      )
      Text(
         style = MaterialTheme.typography.bodyLarge,
         text = stringResource(R.string.years_old, dogAge),
      )
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
   CenterAlignedTopAppBar(
      title = {
         Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
               modifier = Modifier
                  .size(dimensionResource(R.dimen.image_size))
                  .padding(dimensionResource(R.dimen.padding_small)),
               painter = painterResource(R.drawable.ic_woof_logo),
               contentDescription = null
            )

            Text(
               text = stringResource(R.string.app_name),
               style = MaterialTheme.typography.displayLarge
            )
         }
      },
      modifier = modifier
   )
}

/**
 * Composable that displays what the UI of the app looks like in light theme in the design tab.
 */
@Preview(device = "id:4.7in WXGA")
@Composable
fun WoofPreview() {
   WoofTheme(darkTheme = false) {
      WoofApp()
   }
}