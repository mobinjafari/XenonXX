package org.lotka.bp.presentation.ui.recipe_list

import org.lotka.bp.presentation.ui.recipe_list.FoodCategory.BEEF
import org.lotka.bp.presentation.ui.recipe_list.FoodCategory.CHICKEN
import org.lotka.bp.presentation.ui.recipe_list.FoodCategory.DESSERT
import org.lotka.bp.presentation.ui.recipe_list.FoodCategory.DONUT
import org.lotka.bp.presentation.ui.recipe_list.FoodCategory.ERROR
import org.lotka.bp.presentation.ui.recipe_list.FoodCategory.MILK
import org.lotka.bp.presentation.ui.recipe_list.FoodCategory.PIZZA
import org.lotka.bp.presentation.ui.recipe_list.FoodCategory.SOUP
import org.lotka.bp.presentation.ui.recipe_list.FoodCategory.VEGAN
import org.lotka.bp.presentation.ui.recipe_list.FoodCategory.VEGETARIAN
import org.lotka.bp.presentation.ui.recipe_list.FoodCategory.values


enum class FoodCategory(val value: String) {
    ERROR("error"),
    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    MILK("Milk"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donut"),
}

fun getAllFoodCategories(): List<FoodCategory> {
    return listOf(
        ERROR, CHICKEN, BEEF, SOUP, DESSERT, VEGETARIAN, MILK, VEGAN, PIZZA, DONUT
    )
}

fun getFoodCategory(value: String): FoodCategory? {
    val map = values().associateBy(FoodCategory::value)
    return map[value]
}