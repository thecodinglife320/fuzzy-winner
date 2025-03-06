package com.ad.affirmation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
   Card(modifier = modifier) {
      Column {
         Image(
            painter = painterResource(affirmation.imageResourceId),
            contentDescription = stringResource(affirmation.stringResourceId),
            modifier = Modifier
               .fillMaxWidth()
               .height(200.dp),
            contentScale = ContentScale.Crop
         )
         Text(
            text = LocalContext.current.getString(affirmation.stringResourceId),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineSmall
         )
      }
   }
}

@Composable
fun AffirmationList(
   affirmationList: List<Affirmation>,
   modifier: Modifier = Modifier
) {
   LazyColumn(modifier = modifier) {
      items(affirmationList) { affirmation ->
         AffirmationCard(
            affirmation,
            Modifier.padding(8.dp)
         )
      }
   }
}