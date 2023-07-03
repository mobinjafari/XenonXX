package org.lotka.bp.presentation.navigation


import org.lotka.bp.R


sealed class NavigationItem(var route: String, var icon: Int, var title: String) {

    object Welcome : NavigationItem("welcome", R.drawable.ic_home2, "Welcome")
    object Survey : NavigationItem("survey", R.drawable.ic_home2, "Survey")

    object SignIn : NavigationItem("signin/{email}", R.drawable.ic_home2, "SignIn")

    object SignUp : NavigationItem("signup/{email}", R.drawable.ic_home2, "SignUp")
    //bottom nav

    object Dashboard : NavigationItem("dashboard", R.drawable.ic_home2, "Dashboard")

    object Insights : NavigationItem("Insights", R.drawable.ic_grain, "Insights")

    object List : NavigationItem("list", R.drawable.ic_crane_logo, "list")
    object Explore : NavigationItem("explore", R.drawable.nav_explore, "Explore")
    object Profile : NavigationItem("profile", R.drawable.nav_profile, "Profile")

    object Calendar : NavigationItem("calendar", R.drawable.nav_profile, "Calendar")


    object RecipeDetail : NavigationItem("RecipeDetail", R.drawable.nav_profile, "RecipeDetail")
//    object RecipeList: Screen("recipeList")
//
//    object RecipeDetail: Screen("recipeDetail")
}
