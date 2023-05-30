package org.lotka.bp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import org.lotka.bp.datastore.SettingsDataStore
import org.lotka.bp.presentation.navigation.Screen
import org.lotka.bp.presentation.ui.recipe.RecipeDetailScreen
import org.lotka.bp.presentation.ui.recipe.RecipeViewModel
import org.lotka.bp.presentation.ui.recipe_list.RecipeListScreen
import org.lotka.bp.presentation.ui.recipe_list.RecipeListViewModel
import org.lotka.bp.presentation.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

  @Inject
  lateinit var connectivityManager: ConnectivityManager

  @Inject
  lateinit var settingsDataStore: SettingsDataStore

  override fun onStart() {
    super.onStart()
    connectivityManager.registerConnectionObserver(this)
  }

  override fun onDestroy() {
    super.onDestroy()
    connectivityManager.unregisterConnectionObserver(this)
  }

  @SuppressLint("SuspiciousIndentation")
  @ExperimentalComposeUiApi
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // This app draws behind the system bars, so we want to handle fitting system windows
    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
      val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.RecipeList.route) {

          composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeListViewModel = viewModel(this@MainActivity  , "RecipeListViewModel", factory)
            RecipeListScreen(
              isDarkTheme = settingsDataStore.isDark.value,
              isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
              onToggleTheme = settingsDataStore::toggleTheme,
              onNavigateToRecipeDetailScreen = navController::navigate,
              viewModel = viewModel,
            )
          }
          composable(
            route = Screen.RecipeDetail.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
              type = NavType.IntType
            })
          ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeViewModel = viewModel(this@MainActivity ,"RecipeDetailViewModel", factory)
            RecipeDetailScreen(
              isDarkTheme = settingsDataStore.isDark.value,
              isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
              recipeId = navBackStackEntry.arguments?.getInt("recipeId"),
              viewModel = viewModel,
            )
          }
        }

    }
  }


}














