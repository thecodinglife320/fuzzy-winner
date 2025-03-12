package com.ad.monngonmoingay.ui.navigation

@Suppress("ConstPropertyName")
object RecipeDestination {
   const val route = "RecipeScreen"
   const val recipeIdArg = "recipeId"
   const val recipeTitleArg = "recipeName"
   const val routeWithArgs = "$route/{$recipeIdArg}/{$recipeTitleArg}"
}