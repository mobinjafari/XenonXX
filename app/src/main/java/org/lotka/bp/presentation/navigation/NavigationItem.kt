package org.lotka.bp.presentation.navigation


import org.lotka.bp.R


sealed class NavigationItem(var route: String, var icon: Int, var title: String) {


    //bottom nav

    object Dashboard : NavigationItem("dashboard", R.drawable.ic_home2, "Dashboard")

    object Insights : NavigationItem("Insights", R.drawable.ic_grain, "Insights")

    object List : NavigationItem("list", R.drawable.ic_bing, "list")
    object Explore : NavigationItem("explore", R.drawable.nav_explore, "Explore")
    object Profile : NavigationItem("profile", R.drawable.nav_profile, "Profile")


    // listing

}