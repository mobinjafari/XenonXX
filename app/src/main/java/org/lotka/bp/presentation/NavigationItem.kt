package org.lotka.bp.presentation


import org.lotka.bp.R


sealed class NavigationItem(var route: String, var icon: Int, var title: String) {

    object List : NavigationItem("list", R.drawable.ic_launcher_foreground, "list")

    object Home : NavigationItem("home", R.drawable.nav_home, "Home")
    object News : NavigationItem("news", R.drawable.nav_pie_chart, "News")
    object Tabs : NavigationItem("tabs", R.drawable.nav_bag, "Tabs")
    object Apps : NavigationItem("apps", R.drawable.nav_user, "Apps")
    object Profile : NavigationItem("profile", R.drawable.ic_launcher_foreground, "Profile")
}