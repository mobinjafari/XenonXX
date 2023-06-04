package org.lotka.bp.presentation.navigation.util

import org.lotka.bp.presentation.navigation.NavigationItem

 fun shouldShowBottomBar(route: String?): Boolean {
    val bottomBarRoutes = setOf(
        NavigationItem.Dashboard.route,
        NavigationItem.Insights.route,
        NavigationItem.List.route,
        NavigationItem.Explore.route,
        NavigationItem.Profile.route
    )
    return route in bottomBarRoutes
}
