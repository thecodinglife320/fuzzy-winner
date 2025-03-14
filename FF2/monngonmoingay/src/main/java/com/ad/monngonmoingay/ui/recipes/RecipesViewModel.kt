package com.ad.monngonmoingay.ui.recipes

import androidx.lifecycle.SavedStateHandle
import com.ad.monngonmoingay.data.model.Recipe
import com.ad.monngonmoingay.data.repository.RecipeRepository
import com.ad.monngonmoingay.ui.navigation.RecipesDestination
import com.ad.monngonmoingay.ui.shared.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
   savedStateHandle: SavedStateHandle,
   private val recipeRepository: RecipeRepository
) : MainViewModel() {

   var categoryId: String? = savedStateHandle[RecipesDestination.categoryIdArg]
   lateinit var recipeByOrigin: Flow<List<Recipe>>
   lateinit var recipeByMainIngredient: Flow<List<Recipe>>

   fun fetchRecipes() {
      launchCatching {
         recipeByOrigin = recipeRepository.getRecipesByOrigin("$categoryId")
         recipeByMainIngredient = recipeRepository.getRecipesByMainIngredient("$categoryId")
      }
   }
}