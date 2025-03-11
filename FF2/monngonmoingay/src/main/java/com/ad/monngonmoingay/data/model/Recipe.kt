package com.ad.monngonmoingay.data.model

import com.google.firebase.firestore.DocumentId

data class Recipe(
   @DocumentId val recipeId: String = "",
   val title: String = "",
   val description: String = "",
   val image_url: String = "",
   val cook_time: Int = 0,
   val instructions: List<String> = emptyList(),
)

interface Category {
   val categoryId: String
   val name: String
   val img_url: String
}

data class MainIngredient(
   @DocumentId override val categoryId: String = "",
   override val name: String = "",
   override val img_url: String = ""
) : Category

data class Origin(
   @DocumentId override val categoryId: String = "",
   override val name: String = "",
   override val img_url: String = ""
) : Category


