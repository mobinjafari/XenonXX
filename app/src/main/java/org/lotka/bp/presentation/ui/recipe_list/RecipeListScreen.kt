package org.lotka.bp.presentation.ui.recipe_list

import android.annotation.SuppressLint
import android.util.Log

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.lotka.bp.presentation.components.SearchAppBar
import org.lotka.bp.presentation.theme.AppTheme
import org.lotka.bp.util.TAG

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@Composable
fun RecipeListScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    onToggleTheme: () -> Unit,
    onNavigateToAuth: () -> Unit,
    onNavigateToRecipeDetailScreen: (String) -> Unit,
    viewModel: RecipeListViewModel,
) {
    Log.d(TAG, "RecipeListScreen: ${viewModel}")

    val recipes = viewModel.recipes.value

    val query = viewModel.query.value

    val selectedCategory = viewModel.selectedCategory.value

    val loading = viewModel.loading.value

    val page = viewModel.page.value

    val dialogQueue = viewModel.dialogQueue


    AppTheme(
        displayProgressBar = loading,
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable,
        dialogQueue = dialogQueue.queue.value,
    ) {
        Scaffold(
            topBar = {

                SearchAppBar(
                    query = query,
                    onQueryChanged = viewModel::onQueryChanged,
                    onExecuteSearch = {
                        viewModel.onTriggerEvent(RecipeListEvent.NewSearchEvent)
                    },
                    categories = getAllFoodCategories(),
                    selectedCategory = selectedCategory,
                    onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                    onToggleTheme = { onToggleTheme() }
                )
            },
        ) {

        }
    }
}