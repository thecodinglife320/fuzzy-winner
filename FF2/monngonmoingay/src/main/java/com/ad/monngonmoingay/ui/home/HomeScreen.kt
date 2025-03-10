package com.ad.monngonmoingay.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ad.monngonmoingay.R
import com.ad.monngonmoingay.data.model.Category
import com.ad.monngonmoingay.data.model.MainIngredient
import com.ad.monngonmoingay.data.model.Origin
import com.ad.monngonmoingay.ui.shared.LoadingIndicator
import com.ad.monngonmoingay.ui.theme.AppTheme

object HomeDestination {
   const val ROUTE = "HomeScreen"
}

@Composable
fun HomeScreen(
   viewModel: HomeViewModel = hiltViewModel(),
) {

   val isLoadingUser by viewModel.isLoadingUser.collectAsStateWithLifecycle()

   if (isLoadingUser) {
      LoadingIndicator()
   } else {

      val mainIngredients: List<MainIngredient> by viewModel.mainIngredients.collectAsStateWithLifecycle(
         emptyList()
      )
      val origins by viewModel.origins.collectAsStateWithLifecycle(emptyList())
      HomeScreenContent(
         origins = origins,
         mainIngredients = mainIngredients,
      )
   }

   LaunchedEffect(true) {
      viewModel.loadCurrentUser()
   }
}

@Composable
fun HomeScreenContent(
   origins: List<Origin>,
   mainIngredients: List<MainIngredient>,
) {
   Column {
      Text(
         stringResource(R.string.main_ingredient),
         modifier = Modifier.padding(start = dimensionResource(R.dimen.small_pading))
      )
      CategoryRow(categories = mainIngredients)
      Text(
         stringResource(R.string.origin_category), modifier = Modifier.padding(
            start = dimensionResource(R.dimen.small_pading),
            top = dimensionResource(R.dimen.small_pading)
         )
      )
      CategoryRow(categories = origins)
   }
}

@Composable
fun CategoryCard(category: Category) {
   Card(
      Modifier.padding(dimensionResource(R.dimen.small_pading))
   ) {
      Column {
         AsyncImage(
            model = category.img_url,
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.category_photo),
            modifier = Modifier
               .size(height = 100.dp, width = 150.dp)
         )
         Text(
            text = category.name,
         )
      }
   }
}


@Composable
fun CategoryRow(categories: List<Category>) {
   LazyRow {
      items(items = categories, key = { category -> category.categoryId }) { category ->
         CategoryCard(category = category)
      }
   }
}

@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
   AppTheme(darkTheme = false) {
      HomeScreen()
   }
}