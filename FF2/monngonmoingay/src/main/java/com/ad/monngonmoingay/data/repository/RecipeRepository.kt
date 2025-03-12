package com.ad.monngonmoingay.data.repository

import com.ad.monngonmoingay.data.datasource.RecipeRemoteDataSource
import javax.inject.Inject

class RecipeRepository @Inject constructor(
   private val recipeRemoteDataSource: RecipeRemoteDataSource
) {

   //
   val origins = recipeRemoteDataSource.getOrigins()
   val mainIngredients = recipeRemoteDataSource.getMainIngredients()

   //
   fun getRecipesByOrigin(origin: String) = recipeRemoteDataSource.getRecipesByOrigin(origin)
   fun getRecipesByMainIngredient(mainIngredient: String) =
      recipeRemoteDataSource.getRecipesByMainIngredient(mainIngredient)

   fun getRecipeById(recipeId: String) = recipeRemoteDataSource.getRecipeById(recipeId)
}