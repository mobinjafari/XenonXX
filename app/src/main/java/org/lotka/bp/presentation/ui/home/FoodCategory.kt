package org.lotka.bp.presentation.ui.home

import org.lotka.bp.presentation.ui.recipe_list.FoodCategory.*


enum class FoodCategory(val value: String){
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

fun getAllFoodCategories(): List<org.lotka.bp.presentation.ui.recipe_list.FoodCategory>{
    return listOf(
        ERROR, CHICKEN, BEEF, SOUP, DESSERT, VEGETARIAN, MILK, VEGAN, PIZZA, DONUT)
}

fun getFoodCategory(value: String): org.lotka.bp.presentation.ui.recipe_list.FoodCategory? {
    val map = values().associateBy(org.lotka.bp.presentation.ui.recipe_list.FoodCategory::value)
    return map[value]
}