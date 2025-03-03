package com.ad.monngonmoingay.ui.home

import com.ad.monngonmoingay.data.model.MainIngredient
import com.ad.monngonmoingay.data.model.Origin
import com.ad.monngonmoingay.data.repository.AuthRepository
import com.ad.monngonmoingay.data.repository.RecipeRepository
import com.ad.monngonmoingay.ui.shared.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
   private val authRepository: AuthRepository,
   private val recipeRepository: RecipeRepository
) : MainViewModel() {

   private val _isLoadingUserFlow = MutableStateFlow(true)

   val isLoadingUserFlow
      get() = _isLoadingUserFlow.asStateFlow()

   //lateinit var recipesFlow: Flow<List<Recipe>>
   lateinit var originsFlow: Flow<List<Origin>>
   lateinit var mainIngredientsFlow: Flow<List<MainIngredient>>

   init {
      launchCatching {
         originsFlow = recipeRepository.origins
         mainIngredientsFlow = recipeRepository.mainIngredients
      }
   }

   fun loadCurrentUser() {
      launchCatching {
         if (authRepository.currentUser == null) authRepository.createGuestAccount()
         _isLoadingUserFlow.value = false
      }
   }
}