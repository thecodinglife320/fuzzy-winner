package com.ad.hero

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ad.hero.ui.theme.AppTheme

@Composable
fun HeroApp() {
   Scaffold(
      topBar = {
         HeroTopAppBar()
      }
   ) {
      HeroesList(
         heroes = HeroesRepository.heroes,
         contentPadding = it
      )
   }
}

@Composable
fun HeroesList(
   heroes: List<Hero>,
   modifier: Modifier = Modifier,
   contentPadding: PaddingValues = PaddingValues(0.dp),
) {
   val visibleState = remember {
      MutableTransitionState(false).apply {
         // Start the animation immediately.
         targetState = true
      }
   }

   // Fade in entry animation for the entire list
   AnimatedVisibility(
      visibleState = visibleState,
      enter = fadeIn(
         animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
      ),
      exit = fadeOut(),
      modifier = modifier
   ) {
      LazyColumn(contentPadding = contentPadding) {
         itemsIndexed(heroes) { index, hero ->
            HeroItem(
               hero = hero,
               modifier = Modifier
                  .padding(horizontal = 16.dp, vertical = 8.dp)
                  // Animate each list item to slide in vertically
                  .animateEnterExit(
                     enter = slideInVertically(
                        animationSpec = spring(
                           stiffness = StiffnessVeryLow,
                           dampingRatio = DampingRatioLowBouncy
                        ),
                        initialOffsetY = { it * (index + 1) } // staggered entrance
                     )
                  )
            )
         }
      }
   }
}

@Composable
fun HeroItem(
   hero: Hero,
   modifier: Modifier = Modifier
) {
   Card(
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
      modifier = modifier
   ) {
      Row(
         modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium))
            .sizeIn(minHeight = dimensionResource(R.dimen.image_hero_size))
      ) {
         HeroInformation(hero.nameRes, hero.descriptionRes, Modifier.weight(1f))
         Spacer(Modifier.width(16.dp))
         HeroIcon(hero.imageRes)
      }
   }
}

@Composable
fun HeroIcon(
   @DrawableRes heroIcon: Int,
   modifier: Modifier = Modifier
) {
   Image(
      modifier = modifier
         .size(dimensionResource(R.dimen.image_hero_size))
         .clip(RoundedCornerShape(8.dp)),
      painter = painterResource(heroIcon),
      contentDescription = null,
      contentScale = ContentScale.Crop,
   )
}

@Composable
fun HeroInformation(
   @StringRes nameRes: Int,
   @StringRes descriptionRes: Int,
   modifier: Modifier = Modifier
) {
   Column(
      modifier = modifier,
   ) {
      Text(
         text = stringResource(nameRes),
         style = MaterialTheme.typography.displaySmall
      )
      Text(
         modifier = Modifier
            .padding(
               top = dimensionResource(R.dimen.padding_small)
            ),
         style = MaterialTheme.typography.bodyLarge,
         text = stringResource(descriptionRes),
      )
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopAppBar(modifier: Modifier = Modifier) {
   CenterAlignedTopAppBar(
      title = {
         Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.displayLarge
         )
      },
      modifier = modifier
   )
}

@Preview(device = "id:4.7in WXGA", showSystemUi = true)
@Composable
fun HeroAppPreview() {
   AppTheme(darkTheme = false) {
      HeroItem(HeroesRepository.heroes[0])
   }
}