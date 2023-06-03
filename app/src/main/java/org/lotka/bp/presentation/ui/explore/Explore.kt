package org.lotka.bp.presentation.ui.explore


import org.lotka.bp.presentation.ui.explore.ExploreCategory.*

enum class ExploreCategory(val value: String){
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

fun getAllExploreCategories(): List<ExploreCategory>{
    return listOf(
        ERROR, CHICKEN, BEEF, SOUP, DESSERT, VEGETARIAN, MILK, VEGAN, PIZZA, DONUT)
}

fun getExploreCategory(value: String): ExploreCategory? {
    val map = values().associateBy(ExploreCategory::value)
    return map[value]
}