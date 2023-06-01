package org.lotka.bp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.lotka.bp.datastore.SettingsDataStore
import org.lotka.bp.presentation.navigation.Screen
import org.lotka.bp.presentation.theme.navigationDarkThemeBackGroundColor
import org.lotka.bp.presentation.theme.navigationDarkThemeItemColor
import org.lotka.bp.presentation.theme.navigationLightThemeBackGroundColor
import org.lotka.bp.presentation.theme.navigationLightThemeItemColor
import org.lotka.bp.presentation.ui.recipe.RecipeDetailScreen
import org.lotka.bp.presentation.ui.recipe.RecipeViewModel
import org.lotka.bp.presentation.ui.home.RecipeListScreen
import org.lotka.bp.presentation.ui.home.RecipeListViewModel
import org.lotka.bp.presentation.util.ConnectivityManager

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalCoroutinesApi::class,
    ExperimentalComposeUiApi::class
)

@Composable
fun ModernApp(
    activity: MainActivity,
    connectivityManager: ConnectivityManager,
    settingsDataStore: SettingsDataStore
) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar(currentRoute)) {
                BottomNavigationBar(
                    navController = navController,
                    isDarkTheme = settingsDataStore.isDark.value,
                    onToggleTheme = settingsDataStore::toggleTheme,
                    currentRoute = currentRoute
                )
            }

        },
        content = { scaffoldPadding ->

            NavHost(navController, startDestination = NavigationItem.List.route) {
                composable(route = NavigationItem.Home.route) {
                    HomeScreen(
                        paddingValues = scaffoldPadding,
                        onToggleTheme = settingsDataStore::toggleTheme,
                        isDarkTheme = settingsDataStore.isDark.value,
                    )
                }
                composable(route = NavigationItem.Insights.route) {
                    MusicScreen()
                }
                composable(route = NavigationItem.Explore.route) {
                    MoviesScreen()
                }
                composable(route = NavigationItem.Profile.route) {
                    BooksScreen()
                }
                composable(route = NavigationItem.Profile.route) {
                    ProfileScreen()
                }

                composable(
                    route = NavigationItem.List.route
                ) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: RecipeListViewModel =
                        viewModel(activity, "RecipeListViewModel", factory)

                    RecipeListScreen(
                        isDarkTheme = settingsDataStore.isDark.value,
                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                        onToggleTheme = settingsDataStore::toggleTheme,
                        onNavigateToRecipeDetailScreen = navController::navigate,
                        viewModel = viewModel,
                    )
                }


                composable(
                    route = Screen.RecipeDetail.route + "/{recipeId}",
                    arguments = listOf(navArgument("recipeId") {
                        type = NavType.IntType
                    })
                ) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: RecipeViewModel =
                        viewModel(activity, "RecipeDetailViewModel", factory)
                    RecipeDetailScreen(
                        isDarkTheme = settingsDataStore.isDark.value,
                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                        recipeId = navBackStackEntry.arguments?.getInt("recipeId"),
                        viewModel = viewModel,
                    )
                }
            }

        },
    )

}

private fun shouldShowBottomBar(route: String?): Boolean {
    val bottomBarRoutes = setOf(
        NavigationItem.Home.route,
        NavigationItem.Insights.route,
        NavigationItem.List.route,
        NavigationItem.Explore.route,
        NavigationItem.Profile.route
    )
    return route in bottomBarRoutes
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    currentRoute: String?
) {
    val navBackGroundColor: Color
    val navItemColor: Color

    if (isDarkTheme) {
        navBackGroundColor = navigationDarkThemeBackGroundColor
        navItemColor = navigationDarkThemeItemColor
    } else {
        navBackGroundColor = navigationLightThemeBackGroundColor
        navItemColor = navigationLightThemeItemColor
    }
    val navItemFontSize = 10.sp
    val navIconSize = 29.dp
    val navItemFontStyle = MaterialTheme.typography.h3.copy(fontSize = navItemFontSize)


    BottomNavigation(
        Modifier.height(69.dp),
        elevation = 15.dp,
        backgroundColor = navBackGroundColor,
    ) {


        //Home Screen
        BottomNavigationItem(icon = {
            Icon(
                painterResource(id = NavigationItem.Home.icon),
                contentDescription = NavigationItem.Home.title,
                Modifier.size(navIconSize)
            )
        },

            label = {
                Text(
                    text = NavigationItem.Home.title, color = navItemColor, style = navItemFontStyle
                )
            },
            selectedContentColor = navItemColor,
            unselectedContentColor = navItemColor,
            alwaysShowLabel = true,
            selected = currentRoute == NavigationItem.Home.route,
            onClick = {
                navController.navigate(NavigationItem.Home.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            })

        //News
        BottomNavigationItem(icon = {
            Icon(
                painterResource(id = NavigationItem.Insights.icon),
                contentDescription = NavigationItem.Home.title,
                Modifier.size(navIconSize)
            )
        },
            label = {
                Text(
                    text = NavigationItem.Insights.title,
                    color = navItemColor,
                    style = navItemFontStyle
                )
            },
            selectedContentColor = navItemColor,
            unselectedContentColor = navItemColor,
            alwaysShowLabel = true,

            selected = currentRoute == NavigationItem.Insights.route,
            onClick = {
                navController.navigate(NavigationItem.Insights.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            })


        // Bing
        BottomNavigationItem(
            icon = {
                Image(
                    painterResource(id = NavigationItem.List.icon),
                    contentDescription = NavigationItem.List.title,
                    Modifier.padding(vertical = 8.dp)
                )
            },

            selected = currentRoute == NavigationItem.List.route,
            onClick = {
                navController.navigate(NavigationItem.List.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            },

            )


        //Tabs
        BottomNavigationItem(icon = {
            Icon(
                painterResource(id = NavigationItem.Explore.icon),
                contentDescription = NavigationItem.Explore.title,
                Modifier.size(navIconSize)
            )
        },
            label = {
                Text(
                    text = NavigationItem.Explore.title,
                    color = navItemColor,
                    style = navItemFontStyle
                )
            },
            selectedContentColor = navItemColor,
            unselectedContentColor = navItemColor,
            alwaysShowLabel = true,
            selected = currentRoute == NavigationItem.Explore.route,
            onClick = {
                navController.navigate(NavigationItem.Explore.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            })


        // Profile
        BottomNavigationItem(icon = {
            Icon(
                painterResource(id = NavigationItem.Profile.icon),
                contentDescription = NavigationItem.Profile.title,
                Modifier.size(navIconSize)
            )
        },
            label = {
                Text(
                    text = NavigationItem.Profile.title,
                    color = navItemColor,
                    style = navItemFontStyle
                )
            },
            selectedContentColor = navItemColor,
            unselectedContentColor = navItemColor,
            alwaysShowLabel = true,
            selected = currentRoute == NavigationItem.Profile.route,
            onClick = {
                navController.navigate(NavigationItem.Profile.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            })


    }
}
