package com.ad.tipofwellness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ad.tipofwellness.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         AppTheme {
            AppLayout()
         }
      }
   }
}

@Composable
fun AppLayout() {
   Scaffold(
      topBar = { TopAppBar() },
      modifier = Modifier.fillMaxSize()
   ) { innerPadding ->
      LazyColumn(contentPadding = innerPadding) {
         items(tips) {
            TipItem(tip = it, modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
         }
      }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
   CenterAlignedTopAppBar(
      title = {
         Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge
         )
      },
      modifier = modifier
   )
}

@Composable
fun TipItem(tip: Tip, modifier: Modifier = Modifier) {
   Card(modifier = modifier) {
      Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))) {
         Text(
            text = "Day ${tip.day}",
            style = MaterialTheme.typography.titleLarge
         )
         Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
         Text(
            text = stringResource(tip.nameRes),
            style = MaterialTheme.typography.titleLarge
         )
         Image(
            modifier = Modifier
               .fillMaxWidth()
               .padding(vertical = dimensionResource(R.dimen.padding_small))
               .clip(RoundedCornerShape(dimensionResource(R.dimen.image_corner))),
            painter = painterResource(tip.imageRes),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
         )
         Text(
            text = stringResource(tip.descriptionRes),
            style = MaterialTheme.typography.bodyLarge,
         )
      }
   }
}

//@Preview(showBackground = true, showSystemUi = true, device = "id:4.7in WXGA")
//@Composable
//fun GreetingPreview() {
//   AppTheme {
//      TipItem(Tip(R.string.tip1_title, R.string.tip1_desc, R.drawable.pic1,1))
//   }
//}

@Preview
@Composable
fun ItemPreview() {
   AppTheme(darkTheme = true) {
      AppLayout()
   }

}