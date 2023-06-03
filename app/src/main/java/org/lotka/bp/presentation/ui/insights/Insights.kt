package com.example.owl.insights


import com.example.owl.insights.InsightsCategory.*

enum class InsightsCategory(val value: String){
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

fun getAllInsightsCategories(): List<InsightsCategory>{
    return listOf(
        ERROR, CHICKEN, BEEF, SOUP, DESSERT, VEGETARIAN, MILK, VEGAN, PIZZA, DONUT)
}

fun getInsightsCategory(value: String): InsightsCategory? {
    val map = values().associateBy(InsightsCategory::value)
    return map[value]
}