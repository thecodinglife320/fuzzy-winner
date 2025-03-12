package com.ad.monngonmoingay.ui.recipe

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ad.monngonmoingay.data.model.RecipeDetails

@Composable
fun RecipeScreen(
   viewModel: RecipeViewModel = hiltViewModel()
) {
   val recipeDetails by viewModel.recipeDetails.collectAsStateWithLifecycle(RecipeDetails())
   RecipeScreenContent(recipe = recipeDetails)

}

@Composable
fun RecipeScreenContent(recipe: RecipeDetails) {
   Log.d("RecipeScreenContent", "Recipe: $recipe")
}

@Preview(device = "id:4.7in WXGA")
@Composable
fun RecipeScreenContentPreview() {
   RecipeScreenContent(recipe = RecipeDetails())
}
