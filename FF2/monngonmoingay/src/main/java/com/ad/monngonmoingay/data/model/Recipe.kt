package com.ad.monngonmoingay.data.model

import com.google.firebase.firestore.DocumentId

//data transfer object
@Suppress("PropertyName")
data class Recipe(
   @DocumentId val recipeId: String = "",
   val title: String = "",
   val description: String = "",
   val image_url: String = "",
   val prep_time: Int = 0,
   val cook_time: Int = 0,
   val additional_time: Int = 0,
   val total_time: Int = 0,
   val servings: Int = 0,
   val instructions: List<String> = emptyList(),
   val images_per_steps: List<String> = emptyList(),
   val main_ingredient_id: String = "",
   val origin_id: String = ""
)

data class RecipeDetails(
   val recipeId: String = "",
   val title: String = "",
   val description: String = "",
   val imageUrl: String = "",
   val prepTime: String = "",
   val cookTime: String = "",
   val additionalTime: String = "",
   val totalTime: String = "",
   val servings: String = "",
   val instructions: List<String> = emptyList(),
   val imagesPerSteps: List<String> = emptyList(),
)

//extension function
fun Recipe.toRecipeDetails(): RecipeDetails = RecipeDetails(
   recipeId = recipeId,
   title = title,
   description = description,
   imageUrl = image_url,
   prepTime = prep_time.toHoursAndMinutes(),
   cookTime = cook_time.toHoursAndMinutes(),
   additionalTime = additional_time.toHoursAndMinutes(),
   totalTime = total_time.toHoursAndMinutes(),
   servings = "$servings servings",
   instructions = instructions,
   imagesPerSteps = if (images_per_steps.isEmpty()) instructions.map { "" } else images_per_steps
)

//helper function
fun Int.toHoursAndMinutes(): String {
   val hours = this / 60
   val minutes = this % 60
   return if (hours > 0) "${hours}h${minutes}m" else "${minutes}m"
}


