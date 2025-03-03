package com.ad.monngonmoingay.data.datasource

import com.ad.monngonmoingay.data.model.MainIngredient
import com.ad.monngonmoingay.data.model.Origin
import com.ad.monngonmoingay.data.model.Recipe
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RecipeRemoteDataSource @Inject constructor(
   private val firestore: FirebaseFirestore
) {

   fun getRecipes(): Flow<List<Recipe>> {
      return firestore.collection(RECIPE_COLLECTION)
         .dataObjects()
   }

   fun getOrigins(): Flow<List<Origin>> {
      return firestore.collection(ORIGIN_COLLECTION)
         .dataObjects()
   }

   fun getMainIngredients(): Flow<List<MainIngredient>> {
      return firestore.collection(MAIN_INGREDIENTS_COLLECTION)
         .dataObjects()
   }

   companion object {
      private const val RECIPE_COLLECTION = "recipes"
      private const val MAIN_INGREDIENTS_COLLECTION = "mainingredients"
      private const val ORIGIN_COLLECTION = "origins"
   }
}