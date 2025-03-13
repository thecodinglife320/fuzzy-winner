package com.ad.monngonmoingay.ui.recipes

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ad.monngonmoingay.R
import com.ad.monngonmoingay.data.model.Recipe
import com.ad.monngonmoingay.ui.shared.CoilImage
import com.ad.monngonmoingay.ui.theme.AppTheme

@Composable
fun RecipesScreen(
   viewModel: RecipesViewModel = hiltViewModel(),
   navigateToRecipeScreen: (String, String) -> Unit
) {
   val recipesByOrigin by viewModel.recipeByOrigin.collectAsStateWithLifecycle(emptyList())
   val recipesByMainIngredient by viewModel.recipeByMainIngredient.collectAsStateWithLifecycle(
      emptyList()
   )
   val recipes = recipesByOrigin + recipesByMainIngredient
   RecipesScreenContent(
      recipes = recipes,
      navigateToRecipeScreen = navigateToRecipeScreen
   )
}

@Composable
fun RecipesScreenContent(
   recipes: List<Recipe>,
   navigateToRecipeScreen: (String, String) -> Unit
) {
   LazyColumn {
      items(
         items = recipes,
         key = { recipe -> recipe.recipeId }
      ) {
         RecipeCard(
            recipe = it,
            modifier = Modifier
               .fillMaxWidth()
               .padding(dimensionResource(R.dimen.padding_small)),
            navigateToRecipeScreen = navigateToRecipeScreen
         )
      }
   }
}

@Composable
fun RecipeCard(
   recipe: Recipe,
   modifier: Modifier = Modifier,
   navigateToRecipeScreen: (String, String) -> Unit
) {
   var expanded by remember { mutableStateOf(true) }
   Card(
      modifier = modifier
         .clickable {
            navigateToRecipeScreen(recipe.recipeId, recipe.title)
         },
      elevation = CardDefaults.cardElevation(8.dp)
   ) {
      Row(
         verticalAlignment = Alignment.CenterVertically,
      ) {
         CoilImage(
            imageUrl = recipe.image_url,
            modifier = Modifier
               .width(150.dp)
               .height(100.dp)
               .fillMaxWidth()
         )
         Column(
            Modifier
               .padding(start = 8.dp)
               .animateContentSize(
                  animationSpec = spring(
                     dampingRatio = Spring.DampingRatioNoBouncy,
                     stiffness = Spring.StiffnessLow
                  )
               )
         ) {
            //"🔼": show less
            //"🔽": show more
            val icon: String = if (expanded) "🔼" else "🔽"
            Text(
               text = "${recipe.title} $icon",
               style = MaterialTheme.typography.titleSmall,
               modifier = Modifier.clickable { expanded = !expanded }
            )
            if (expanded) {
               RecipeInfo(
                  description = recipe.description,
                  textStyle = MaterialTheme.typography.bodySmall
               )
            }
         }
      }
   }
}

@Composable
fun RecipeInfo(
   description: String,
   modifier: Modifier = Modifier,
   textStyle: TextStyle
) {
   Row(
      modifier = modifier.height(IntrinsicSize.Min) // Giới hạn chiều cao bằng nội dung
   ) {
      VerticalDivider(
         thickness = 5.dp,
      )
      Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))
      Text(
         "📖 $description",
         maxLines = 2,
         style = textStyle
      )
   }
}

@Preview(showSystemUi = true, device = "id:4.7in WXGA")
@Composable
fun RecipeCardPreview() {
   AppTheme {
      RecipeCard(
         recipe = Recipe(
            title = "Bún chả",
            image_url = "https://images.pexels.com/photos/1256875/pexels-photo-1256875.jpeg?auto=compress&cs=tinysrgb&w=600",
            recipeId = "",
            description = "A rich and creamy pasta dish with garlic, Parmesan cheese, and a hint of butter.",
            cook_time = 45,
            instructions = listOf()
         ),
         modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small)),
         navigateToRecipeScreen = { _, _ -> }
      )
//      RecipeInfo(
//         cookTime = "10 minute",
//         description = "A rich and creamy pasta dish with garlic, Parmesan cheese, and a hint of butter.",
//         modifier =Modifier
//      )
   }
}

