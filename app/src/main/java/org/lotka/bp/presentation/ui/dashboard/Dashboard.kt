package org.lotka.bp.presentation.ui.dashboard


import org.lotka.bp.presentation.ui.dashboard.DashboardCategory.*

enum class DashboardCategory(val value: String){
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

fun getAllDashboardCategories(): List<DashboardCategory>{
    return listOf(
        ERROR, CHICKEN, BEEF, SOUP, DESSERT, VEGETARIAN, MILK, VEGAN, PIZZA, DONUT)
}

fun getDashboardCategory(value: String): DashboardCategory? {
    val map = values().associateBy(DashboardCategory::value)
    return map[value]
}