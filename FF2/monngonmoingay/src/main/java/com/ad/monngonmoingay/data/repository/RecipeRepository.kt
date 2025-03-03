package com.ad.monngonmoingay.data.repository

import com.ad.monngonmoingay.data.datasource.RecipeRemoteDataSource
import javax.inject.Inject

class RecipeRepository @Inject constructor(
   private val recipeRemoteDataSource: RecipeRemoteDataSource
) {
   //val recipes = recipeRemoteDataSource.getRecipes()
   val origins = recipeRemoteDataSource.getOrigins()
   val mainIngredients = recipeRemoteDataSource.getMainIngredients()
}