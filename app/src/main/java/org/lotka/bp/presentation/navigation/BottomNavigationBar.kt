package org.lotka.bp.presentation.navigation


import android.os.Build
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.samples.crane.home.CraneHome
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.owl.insights.InsightsScreen
import com.example.owl.insights.InsightsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.lotka.bp.datastore.SettingsDataStore
import org.lotka.bp.presentation.navigation.BottomNavigationBar
import org.lotka.bp.presentation.navigation.NavigationItem
import org.lotka.bp.presentation.theme.navigationDarkThemeBackGroundColor
import org.lotka.bp.presentation.theme.navigationDarkThemeItemColor
import org.lotka.bp.presentation.theme.navigationLightThemeBackGroundColor
import org.lotka.bp.presentation.theme.navigationLightThemeItemColor
import org.lotka.bp.presentation.ui.dashboard.DashboardScreen
import org.lotka.bp.presentation.ui.dashboard.DashboardViewModel
import org.lotka.bp.presentation.ui.explore.ExploreScreen
import org.lotka.bp.presentation.ui.explore.ExploreViewModel
import org.lotka.bp.presentation.ui.profile.ProfileScreen
import org.lotka.bp.presentation.ui.profile.ProfileViewModel
import org.lotka.bp.presentation.ui.recipe.RecipeDetailScreen
import org.lotka.bp.presentation.ui.recipe.RecipeViewModel
import org.lotka.bp.presentation.ui.recipe_list.RecipeListScreen
import org.lotka.bp.presentation.ui.recipe_list.RecipeListViewModel
import org.lotka.bp.presentation.ui.signinsignup.SignInRoute
import org.lotka.bp.presentation.ui.signinsignup.SignUpRoute
import org.lotka.bp.presentation.ui.signinsignup.WelcomeRoute
import org.lotka.bp.presentation.ui.survey.SurveyRoute
import org.lotka.bp.presentation.util.ConnectivityManager


@Composable
fun BottomNavigationBar(
    navController: NavController,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    currentRoute: String?,

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


        //Dashboard Screen
        BottomNavigationItem(icon = {
            Icon(
                painterResource(id = NavigationItem.Dashboard.icon),
                contentDescription = NavigationItem.Dashboard.title,
                Modifier.size(navIconSize)
            )
        },

            label = {
                Text(
                    text = NavigationItem.Dashboard.title, color = navItemColor, style = navItemFontStyle
                )
            },
            selectedContentColor = navItemColor,
            unselectedContentColor = navItemColor,
            alwaysShowLabel = true,
            selected = currentRoute == NavigationItem.Dashboard.route,
            onClick = {
                navController.navigate(NavigationItem.Dashboard.route) {
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

        //Insights Screen
        BottomNavigationItem(icon = {
            Icon(
                painterResource(id = NavigationItem.Insights.icon),
                contentDescription = NavigationItem.Insights.title,
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


        //Explore Screen
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