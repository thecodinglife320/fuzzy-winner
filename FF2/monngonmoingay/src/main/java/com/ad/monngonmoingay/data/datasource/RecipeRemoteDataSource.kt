package com.ad.monngonmoingay.data.datasource

import com.ad.monngonmoingay.data.model.MainIngredient
import com.ad.monngonmoingay.data.model.Origin
import com.ad.monngonmoingay.data.model.Recipe
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class RecipeRemoteDataSource @Inject constructor(
   private val firestore: FirebaseFirestore
) {

   fun getRecipesByOrigin(origin: String): Flow<List<Recipe>> {
      return firestore.collection(RECIPE_COLLECTION)
         .whereEqualTo("origin_id", origin)
         .dataObjects()
   }

   fun getRecipesByMainIngredient(mainIngredient: String): Flow<List<Recipe>> {
      return firestore.collection(RECIPE_COLLECTION)
         .whereEqualTo("main_ingredient_id", mainIngredient)
         .dataObjects()
   }

   fun getOrigins(): Flow<List<Origin>> {
      return firestore.collection(ORIGIN_COLLECTION)
         .dataObjects()
   }

   fun getRecipeById(recipeId: String): Flow<Recipe?> {
      return firestore.collection(RECIPE_COLLECTION)
         .document(recipeId)
         .dataObjects()
   }

   fun getMainIngredients(): Flow<List<MainIngredient>> {
      return firestore.collection(MAIN_INGREDIENTS_COLLECTION)
         .dataObjects()
   }

   suspend fun createRecipe(recipe: Recipe): String {
      return firestore.collection(RECIPE_COLLECTION).add(recipe).await().id
   }

   companion object {
      private const val RECIPE_COLLECTION = "recipes"
      private const val MAIN_INGREDIENTS_COLLECTION = "mainingredients"
      private const val ORIGIN_COLLECTION = "origins"
   }
}