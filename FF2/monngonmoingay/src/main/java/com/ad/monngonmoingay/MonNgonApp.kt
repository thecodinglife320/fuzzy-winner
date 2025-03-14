package com.ad.monngonmoingay

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ad.monngonmoingay.data.model.ErrorMessage
import com.ad.monngonmoingay.ui.home.HomeScreen
import com.ad.monngonmoingay.ui.home.HomeViewModel
import com.ad.monngonmoingay.ui.login.SignInScreen
import com.ad.monngonmoingay.ui.navigation.HomeDestination
import com.ad.monngonmoingay.ui.navigation.RecipeDestination
import com.ad.monngonmoingay.ui.navigation.RecipesDestination
import com.ad.monngonmoingay.ui.navigation.SignInDestination
import com.ad.monngonmoingay.ui.navigation.SignUpDestination
import com.ad.monngonmoingay.ui.recipe.RecipeScreen
import com.ad.monngonmoingay.ui.recipes.RecipesScreen
import com.ad.monngonmoingay.ui.recipes.RecipesViewModel
import com.ad.monngonmoingay.ui.setting.SettingDestination
import com.ad.monngonmoingay.ui.setting.SettingsScreen
import com.ad.monngonmoingay.ui.signup.SignUpScreen
import kotlinx.coroutines.launch

@Composable
fun MonNgonApp() {

   val scope = rememberCoroutineScope()
   val snackBarHostState = remember { SnackbarHostState() }
   val navController = rememberNavController()
   val navBackStackEntry by navController.currentBackStackEntryAsState()
   var titleAppBar = ""

   when (navBackStackEntry?.destination?.route) {
      HomeDestination.route -> titleAppBar = HomeDestination.title
      RecipesDestination.routeWithArgs -> {
         val categoryName =
            navBackStackEntry?.arguments?.getString(RecipesDestination.categoryNameArg) ?: "Unknown"
         titleAppBar = "$categoryName food"
      }

      RecipeDestination.routeWithArgs -> {
         val recipeTitle =
            navBackStackEntry?.arguments?.getString(RecipeDestination.recipeTitleArg) ?: "Unknown"
         titleAppBar = recipeTitle
      }

      SettingDestination.route -> titleAppBar = SettingDestination.title
      SignInDestination.route -> titleAppBar = SignInDestination.title
      SignUpDestination.route -> titleAppBar = SignUpDestination.title
   }

   val shouldShowSettings = !(titleAppBar == "Sign In" ||
        titleAppBar == "Sign Up")

   Scaffold(
      topBar = {
         MonNgonAppBar(
            titleAppBar = titleAppBar,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() },
            actionSettingIcon = {
               navController.navigate(SettingDestination.route) {
                  launchSingleTop = true
               }
            },
            shouldShowSettings = shouldShowSettings,
         )
      },
      floatingActionButton = {
         if (titleAppBar == HomeDestination.title) {
            val viewModel: HomeViewModel? = navBackStackEntry?.let { hiltViewModel(it) }
            FloatingActionButton(onClick = { viewModel?.createRecipes() }) {
               Icon(Icons.Outlined.Add, "Add")
            }
         }
      },
      modifier = Modifier.fillMaxSize(),
      snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
   ) { innerPadding ->
      NavHost(
         navController = navController,
         startDestination = HomeDestination.route,
         modifier = Modifier.padding(innerPadding)
      ) {

         //sign in screen
         composable(SignInDestination.route) {
            val context = LocalContext.current
            SignInScreen(
               restartApp = {
                  navController.popBackStack(HomeDestination.route, false)
               },
               openSignUpScreen = {
                  navController.navigate(SignUpDestination.route) {
                     launchSingleTop = true
                  }
               },
               showErrorSnackBar = { errorMessage ->
                  val message = when (errorMessage) {
                     is ErrorMessage.StringError -> errorMessage.message
                     is ErrorMessage.IdError -> context.getString(errorMessage.message)
                  }
                  scope.launch { snackBarHostState.showSnackbar(message) }
               },
            )
         }

         //sign up screen
         composable(SignUpDestination.route) {
            val context = LocalContext.current
            SignUpScreen(
               restartApp = {
                  navController.popBackStack(HomeDestination.route, false)
               },
               showErrorSnackBar = { errorMessage ->
                  val message = when (errorMessage) {
                     is ErrorMessage.StringError -> errorMessage.message
                     is ErrorMessage.IdError -> context.getString(errorMessage.message)
                  }
                  scope.launch {
                     snackBarHostState.showSnackbar(message)
                  }
               },
            )
         }

         //home screen
         composable(HomeDestination.route) {
            HomeScreen(
               navigateToRecipesScreen = { categoryId, categoryName ->
                  navController.navigate("${RecipesDestination.route}/$categoryId/$categoryName")
               }
            )
         }

         //recipes screen
         composable(
            route = RecipesDestination.routeWithArgs,
            arguments = listOf(
               navArgument(
                  RecipesDestination.categoryIdArg,
                  builder = { type = NavType.StringType }
               )
            )
         ) {
            val parentEntry = remember { navController.getBackStackEntry(HomeDestination.route) }

            val recipesViewModel = hiltViewModel<RecipesViewModel>(parentEntry)
            recipesViewModel.categoryId = it.arguments?.getString(RecipesDestination.categoryIdArg)
            recipesViewModel.fetchRecipes()
            RecipesScreen(
               navigateToRecipeScreen = { recipeId, recipeTitle ->
                  navController.navigate("${RecipeDestination.route}/$recipeId/$recipeTitle")
               },
               viewModel = recipesViewModel
            )
         }

         //recipe screen
         composable(
            route = RecipeDestination.routeWithArgs,
            arguments = listOf(
               navArgument(
                  RecipeDestination.recipeIdArg,
                  builder = { type = NavType.StringType }
               )
            )
         ) {
            RecipeScreen(hiltViewModel(it))
         }

         //setting screen
         composable(SettingDestination.route) {
            SettingsScreen(
               openHomeScreen = {
                  navController.popBackStack(HomeDestination.route, false)
               },
               openSignInScreen = {
                  navController.navigate(SignInDestination.route) {
                     launchSingleTop = true
                  }
               },
            )
         }
      }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MonNgonAppBar(
   titleAppBar: String,
   canNavigateBack: Boolean,
   navigateUp: () -> Unit,
   actionSettingIcon: () -> Unit,
   shouldShowSettings: Boolean,
   modifier: Modifier = Modifier
) {
   TopAppBar(
      title = { Text(text = titleAppBar) },
      modifier = modifier,
      navigationIcon = {
         if (canNavigateBack) {
            IconButton(onClick = navigateUp) {
               Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                  contentDescription = ""
               )
            }
         }
      },
      actions = {
         if (shouldShowSettings)
            IconButton(onClick = { actionSettingIcon() }) {
               Icon(
                  imageVector = Icons.Filled.Settings,
                  contentDescription = stringResource(R.string.settings)
               )
            }
      },
   )
}