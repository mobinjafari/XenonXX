package org.lotka.bp.presentation


import org.lotka.bp.R


sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_launcher_foreground, "Home")
    object Music : NavigationItem("music", R.drawable.ic_launcher_foreground, "Music")
    object Movies : NavigationItem("movies", R.drawable.ic_launcher_foreground, "Movies")
    object Books : NavigationItem("books", R.drawable.ic_launcher_foreground, "Books")
    object Profile : NavigationItem("profile", R.drawable.ic_launcher_foreground, "Profile")
}