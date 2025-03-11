package com.ad.monngonmoingay.ui.recipes

import androidx.lifecycle.SavedStateHandle
import com.ad.monngonmoingay.data.repository.RecipeRepository
import com.ad.monngonmoingay.ui.shared.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
   savedStateHandle: SavedStateHandle,
   private val recipeRepository: RecipeRepository
) : MainViewModel() {

   private val categoryId: String = checkNotNull(savedStateHandle[RecipesDestination.categoryIdArg])
   val recipeByOrigin = recipeRepository.getRecipesByOrigin(categoryId)
   val recipeByMainIngredient = recipeRepository.getRecipesByMainIngredient(categoryId)

}