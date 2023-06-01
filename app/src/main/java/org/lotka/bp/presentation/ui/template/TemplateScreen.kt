package org.lotka.bp.presentation.ui.template

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.lotka.bp.presentation.components.RecipeList
import org.lotka.bp.presentation.theme.AppTheme
import org.lotka.bp.util.TAG


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun TemplateScreen(
  isDarkTheme: Boolean,
  isNetworkAvailable: Boolean,
  onToggleTheme: () -> Unit,
  onNavigateToRecipeDetailScreen: (String) -> Unit,
  viewModel: TemplateViewModel,
) {
  Log.d(TAG, "RecipeListScreen: ${viewModel}")

  val recipes = viewModel.recipes.value

  val query = viewModel.query.value

  val selectedCategory = viewModel.selectedCategory.value

  val loading = viewModel.loading.value

  val page = viewModel.page.value

  val dialogQueue = viewModel.dialogQueue

  val scaffoldState = rememberScaffoldState()

  AppTheme(
    displayProgressBar = loading,
    scaffoldState = scaffoldState,
    darkTheme = isDarkTheme,
    isNetworkAvailable = isNetworkAvailable,
    dialogQueue = dialogQueue.queue.value,
  ) {
    Scaffold(

      scaffoldState = scaffoldState,
      snackbarHost = {
        scaffoldState.snackbarHostState
      },
    ) {
      RecipeList(
        loading = loading,
        recipes = recipes,
        onChangeScrollPosition = viewModel::onChangeRecipeScrollPosition,
        page = page,
        onTriggerNextPage = { viewModel.onTriggerEvent(TemplateEvent.NextPageEvent) },
        onNavigateToRecipeDetailScreen = onNavigateToRecipeDetailScreen
      )
    }
  }
}