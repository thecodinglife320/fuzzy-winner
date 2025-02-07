package com.ad.ff2.gridcourse

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ad.ff2.R

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
   Card(modifier = modifier) {
      Row {
         ImageTopic(image = topic.image, contentDescription = topic.name)
         InfoTopic(name = topic.name, numberOfCourses = topic.numberOfCourses)
      }
   }
}

@Composable
fun ImageTopic(image: Int, contentDescription: Int, modifier: Modifier = Modifier) {
   Image(
      painter = painterResource(id = image),
      contentDescription = stringResource(id = contentDescription),
      modifier = modifier
         .height(68.dp)
         .width(68.dp)
   )
}

@Composable
fun InfoTopic(name: Int, numberOfCourses: Int, modifier: Modifier = Modifier) {
   Column {
      Text(
         text = stringResource(id = name),
         style = MaterialTheme.typography.bodyMedium,
         modifier = modifier.padding(start = 8.dp, end = 16.dp, top = 8.dp)
      )
      Row(
         modifier = modifier
            .padding(top = 8.dp),
         verticalAlignment = CenterVertically
      ) {
         Icon(
            imageVector = Icons.Default.Place,
            contentDescription = "Icon",
            modifier = modifier.padding(end = 8.dp, start = 8.dp)
         )
         Text(
            text = "$numberOfCourses",
            style = MaterialTheme.typography.labelMedium,
         )
      }
   }
}

@Composable
fun TopicCardGrid(
   topics: List<Topic>,
   modifier: Modifier = Modifier
) {
   LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
      horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
      modifier = modifier
   ) {
      items(topics) { topic ->
         TopicCard(topic)
      }
   }
}

@Composable
fun TopicCourseLayout() {
   val layoutDirection = LocalLayoutDirection.current
   Column(
      modifier = Modifier
         .fillMaxSize()
         .statusBarsPadding()
         .padding(
            start = WindowInsets.safeDrawing.asPaddingValues()
               .calculateStartPadding(layoutDirection),
            end = WindowInsets.safeDrawing.asPaddingValues()
               .calculateEndPadding(layoutDirection),
         ),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Text(
         "Topics",
         style = MaterialTheme.typography.bodyLarge,
         fontWeight = FontWeight.SemiBold,
         fontSize = 20.sp
      )
      TopicCardGrid(
         topics = DataSource.topics,
      )
   }
}

@Preview(device = "id:4.7in WXGA", showSystemUi = true)
@Composable
fun TopicCourseLayoutPreview() {
   TopicCourseLayout()
}