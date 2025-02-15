package com.ad.dessertshop.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ad.dessertshop.R
import com.ad.dessertshop.ui.stateholder.DessertShopViewModel
import com.ad.dessertshop.ui.theme.FF2Theme

@Composable
fun TopAppBar(
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
private fun DessertShop(
   @DrawableRes dessertImageId: Int,
   onDessertClicked: () -> Unit,
   modifier: Modifier = Modifier
) {
   Box {
      Image(
         painter = painterResource(R.drawable.bakery_back),
         contentDescription = null,
         contentScale = ContentScale.FillHeight,
         modifier = modifier
      )
      Image(
         painter = painterResource(dessertImageId),
         contentDescription = null,
         modifier = Modifier
            .align(Alignment.Center)
            .clickable { onDessertClicked() }
            .padding(bottom = 80.dp)
      )
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

@Composable
fun DessertShopApp(
   dessertShopViewModel: DessertShopViewModel = viewModel()
) {

   val dessertShopUiState by dessertShopViewModel.uiStateFlow.collectAsState()

   Scaffold(
      topBar = {
         val intentContext = LocalContext.current
         TopAppBar(
            onShareButtonClicked = { dessertShopViewModel.shareSoldDessertsInformation(intentContext) },
            modifier = Modifier
               .fillMaxWidth()
               .padding(top = 16.dp)
         )
      }
   ) { paddingValues ->
      Column(modifier = Modifier.padding(paddingValues)) {
         DessertShop(
            dessertImageId = dessertShopUiState.currentDessert?.imageId!!,
            onDessertClicked = { dessertShopViewModel.soldDessert() },
            Modifier.fillMaxHeight(0.88f)
         )
         TransactionInfo(
            revenue = dessertShopUiState.revenue,
            dessertsSold = dessertShopUiState.dessertsSold
         )
      }
   }
}

@Preview(showBackground = true, showSystemUi = true, apiLevel = 31, device = "id:4.7in WXGA")
@Composable
private fun MyDessertClickerAppPreview() {
   FF2Theme {
      DessertShopApp()
   }
}
