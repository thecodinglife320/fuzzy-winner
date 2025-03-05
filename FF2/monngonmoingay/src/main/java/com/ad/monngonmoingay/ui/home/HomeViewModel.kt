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

   private val _isLoadingUser = MutableStateFlow(true)

   val isLoadingUser
      get() = _isLoadingUser.asStateFlow()

   lateinit var origins: Flow<List<Origin>>
   lateinit var mainIngredients: Flow<List<MainIngredient>>

   init {
      launchCatching {
         origins = recipeRepository.origins
         mainIngredients = recipeRepository.mainIngredients
      }
   }

   fun loadCurrentUser() {
      launchCatching {
         if (authRepository.currentUser == null) authRepository.createGuestAccount()
         _isLoadingUser.value = false
      }
   }
}