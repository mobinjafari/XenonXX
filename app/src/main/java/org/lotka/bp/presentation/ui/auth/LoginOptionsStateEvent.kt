package org.lotka.bp.presentation.ui.auth

sealed class RecipeEvent {

    data class GetRecipeEvent(
        val id: Int
    ) : RecipeEvent()

}