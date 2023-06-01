package org.lotka.bp.presentation


import org.lotka.bp.R


sealed class NavigationItem(var route: String, var icon: Int, var title: String) {

    //bottom nav
    object List : NavigationItem("list", R.drawable.ic_bing, "list")
    object Home : NavigationItem("home", R.drawable.ic_home2, "Home")
    object Insights : NavigationItem("Insights", R.drawable.ic_grain, "Insights")
    object Explore : NavigationItem("explore", R.drawable.nav_explore, "Explore")
    object Profile : NavigationItem("profile", R.drawable.nav_profile, "Profile")


    // listing
}