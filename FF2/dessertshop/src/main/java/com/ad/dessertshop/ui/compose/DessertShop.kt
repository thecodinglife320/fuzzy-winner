package com.ad.dessertshop.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.ad.dessertshop.R
import com.ad.dessertshop.ui.theme.FF2Theme

//@Composable
//fun DessertShopApp(
//   desserts: List<Dessert>
//) {
//   Scaffold(
//      topBar = {
//         val intentContext = LocalContext.current
//         val layoutDirection = LocalLayoutDirection.current
//         DessertClickerAppBar(
//            onShareButtonClicked = {
//               shareSoldDessertsInformation(
//                  intentContext = intentContext,
//                  dessertsSold = dessertsSold,
//                  revenue = revenue
//               )
//            },
//            modifier = Modifier
//               .fillMaxWidth()
//               .padding(
//                  start = WindowInsets.safeDrawing.asPaddingValues()
//                     .calculateStartPadding(layoutDirection),
//                  end = WindowInsets.safeDrawing.asPaddingValues()
//                     .calculateEndPadding(layoutDirection),
//               )
//               .background(MaterialTheme.colorScheme.primary)
//         )
//      }
//   ) { contentPadding ->
//      DessertShop(
//         revenue = revenue,
//         dessertsSold = dessertsSold,
//         dessertImageId = currentDessertImageId,
//         onDessertClicked = {
//
//            // Update the revenue
//            revenue += currentDessertPrice
//            dessertsSold++
//
//            // Show the next dessert
//            val dessertToShow = determineDessertToShow(desserts, dessertsSold)
//            currentDessertImageId = dessertToShow.imageId
//            currentDessertPrice = dessertToShow.price
//         },
//         modifier = Modifier.padding(contentPadding)
//      )
//   }
//}

@Composable
private fun TopAppBar(
   onShareButtonClicked: () -> Unit,
   modifier: Modifier = Modifier
) {
   Row(
      modifier = modifier,
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
   ) {
      Text(
         text = stringResource(R.string.app_name),
         modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium)),
         style = MaterialTheme.typography.titleLarge,
      )
      IconButton(
         onClick = onShareButtonClicked,
         modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_medium)),
      ) {
         Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = stringResource(R.string.share),
         )
      }
   }
}

@Composable
fun DessertShop(
   revenue: Int,
   dessertsSold: Int,
   @DrawableRes dessertImageId: Int,
   onDessertClicked: () -> Unit,
   modifier: Modifier = Modifier
) {
   Box(modifier = modifier) {
      Image(
         painter = painterResource(R.drawable.bakery_back),
         contentDescription = null,
         contentScale = ContentScale.FillHeight,
         modifier = Modifier.fillMaxSize()
      )
      Column {
         Box(
            modifier = Modifier
               .weight(1f)
               .fillMaxWidth(),
         ) {
            Image(
               painter = painterResource(dessertImageId),
               contentDescription = null,
               modifier = Modifier
                  .width(dimensionResource(R.dimen.image_size))
                  .height(dimensionResource(R.dimen.image_size))
                  .align(Alignment.Center)
                  .clickable { onDessertClicked() },
               contentScale = ContentScale.Crop,
            )
         }
         TransactionInfo(
            revenue = revenue,
            dessertsSold = dessertsSold,
            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
         )
      }
   }
}

@Composable
private fun TransactionInfo(
   revenue: Int,
   dessertsSold: Int,
   modifier: Modifier = Modifier
) {
   Column(
      modifier = modifier
         .fillMaxWidth()
   ) {

      //so luong da ban
      Row(
         modifier = Modifier.fillMaxWidth(),
         horizontalArrangement = Arrangement.SpaceBetween
      ) {
         Text(
            text = stringResource(R.string.dessert_sold),
            style = MaterialTheme.typography.titleLarge,
         )
         Text(
            text = dessertsSold.toString(),
            style = MaterialTheme.typography.titleLarge,
         )
      }

      //doanh thu
      Row(
         modifier = Modifier.fillMaxWidth(),
         horizontalArrangement = Arrangement.SpaceBetween,
      ) {
         Text(
            text = stringResource(R.string.total_revenue),
            style = MaterialTheme.typography.headlineMedium,
         )
         Text(
            text = "$${revenue}",
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.headlineMedium,
         )
      }
   }
}

@Preview(showBackground = true)
@Composable
private fun MyDessertClickerAppPreview() {
   FF2Theme {
      TopAppBar(onShareButtonClicked = {})
   }
}
