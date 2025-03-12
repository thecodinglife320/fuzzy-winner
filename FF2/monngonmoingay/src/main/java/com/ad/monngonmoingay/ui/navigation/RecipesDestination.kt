package com.ad.monngonmoingay.ui.navigation

@Suppress("ConstPropertyName")
object RecipesDestination : NavigationDestination {
   override val route = "RecipesScreen"
   override val title: String
      get() = ""
   const val categoryIdArg = "categoryId"
   const val categoryNameArg = "categoryName"
   val routeWithArgs = "$route/{$categoryIdArg}/{$categoryNameArg}"
}