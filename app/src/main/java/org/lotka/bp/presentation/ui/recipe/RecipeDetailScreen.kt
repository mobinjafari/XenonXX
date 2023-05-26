package org.lotka.bp.presentation.ui.recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.lotka.bp.presentation.components.IMAGE_HEIGHT
import org.lotka.bp.presentation.components.LoadingRecipeShimmer
import org.lotka.bp.presentation.components.RecipeView
import org.lotka.bp.presentation.theme.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@Composable
fun RecipeDetailScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    recipeId: Int?,
    viewModel: RecipeViewModel,
) {
    if (recipeId == null) {
        TODO("Show Invalid Recipe")
    } else {
        // fire a one-off event to get the recipe from api
        val onLoad = viewModel.onLoad.value
        if (!onLoad) {
            viewModel.onLoad.value = true
            viewModel.onTriggerEvent(RecipeEvent.GetRecipeEvent(recipeId))
        }

        val loading = viewModel.loading.value

        val recipe = viewModel.recipe.value

        val dialogQueue = viewModel.dialogQueue


        AppTheme(
            displayProgressBar = loading,
            darkTheme = isDarkTheme,
            isNetworkAvailable = isNetworkAvailable,
            dialogQueue = dialogQueue.queue.value,
        ) {
            Scaffold(
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (loading && recipe == null) {
                        LoadingRecipeShimmer(imageHeight = IMAGE_HEIGHT.dp)
                    } else if (!loading && recipe == null && onLoad) {
                        TODO("Show Invalid Recipe")
                    } else {
                        recipe?.let { RecipeView(recipe = it) }
                    }
                }
            }
        }
    }
}