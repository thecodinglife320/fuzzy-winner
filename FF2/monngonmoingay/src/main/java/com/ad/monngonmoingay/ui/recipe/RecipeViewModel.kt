package com.ad.monngonmoingay.ui.recipe

import androidx.lifecycle.SavedStateHandle
import com.ad.monngonmoingay.data.model.RecipeDetails
import com.ad.monngonmoingay.data.model.toRecipeDetails
import com.ad.monngonmoingay.data.repository.RecipeRepository
import com.ad.monngonmoingay.ui.navigation.RecipeDestination
import com.ad.monngonmoingay.ui.shared.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
   savedStateHandle: SavedStateHandle,
   private val recipeRepository: RecipeRepository
) : MainViewModel() {

   private val recipeId: String = checkNotNull(savedStateHandle[RecipeDestination.recipeIdArg])
   lateinit var recipeDetails: Flow<RecipeDetails>
   private val _stepIndex = MutableStateFlow(0)
   val stepIndex = _stepIndex.asStateFlow()

   init {
      launchCatching {
         val recipe = recipeRepository.getRecipeById(recipeId)
         recipeDetails = recipe.map {
            it?.toRecipeDetails() ?: RecipeDetails()
         }
      }
   }

   fun onNextStep() {
      _stepIndex.value++
   }

   fun onPreviousStep() {
      _stepIndex.value--
   }
}