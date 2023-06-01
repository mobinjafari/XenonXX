package org.lotka.bp.presentation.ui.template


import org.lotka.bp.presentation.ui.template.TemplateCategory.*

enum class TemplateCategory(val value: String){
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

fun getAllTemplateCategories(): List<TemplateCategory>{
    return listOf(
        ERROR, CHICKEN, BEEF, SOUP, DESSERT, VEGETARIAN, MILK, VEGAN, PIZZA, DONUT)
}

fun getTemplateCategory(value: String): TemplateCategory? {
    val map = values().associateBy(TemplateCategory::value)
    return map[value]
}