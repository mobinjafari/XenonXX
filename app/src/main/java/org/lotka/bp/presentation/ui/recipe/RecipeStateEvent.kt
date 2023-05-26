package org.lotka.bp.presentation.ui.recipe

sealed class RecipeEvent {

    data class GetRecipeEvent(
        val id: Int
    ) : RecipeEvent()

}