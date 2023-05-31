package org.lotka.bp.presentation



import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import org.lotka.bp.datastore.SettingsDataStore
import org.lotka.bp.presentation.navigation.Screen
import org.lotka.bp.presentation.ui.recipe.RecipeDetailScreen
import org.lotka.bp.presentation.ui.recipe.RecipeViewModel
import org.lotka.bp.presentation.ui.recipe_list.RecipeListScreen
import org.lotka.bp.presentation.ui.recipe_list.RecipeListViewModel
import org.lotka.bp.presentation.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    @SuppressLint("SuspiciousIndentation")
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            val navController = rememberNavController()

            Scaffold(
                topBar = { TopBar() },
                bottomBar = { BottomNavigationBar(navController) },
                content = { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        NavHost(navController, startDestination = NavigationItem.List.route) {
                            composable(route=NavigationItem.Home.route) {
                                HomeScreen()
                            }
                            composable(route=NavigationItem.Music.route) {
                                MusicScreen()
                            }
                            composable(route=NavigationItem.Movies.route) {
                                MoviesScreen()
                            }
                            composable(route=NavigationItem.Books.route) {
                                BooksScreen()
                            }
                            composable(route=NavigationItem.Profile.route) {
                                ProfileScreen()
                            }

                            composable(route = NavigationItem.List.route) { navBackStackEntry ->
                                val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                                val viewModel: RecipeListViewModel =
                                    viewModel(this@MainActivity, "RecipeListViewModel", factory)
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
                                    viewModel(this@MainActivity, "RecipeDetailViewModel", factory)
                                RecipeDetailScreen(
                                    isDarkTheme = settingsDataStore.isDark.value,
                                    isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                                    recipeId = navBackStackEntry.arguments?.getInt("recipeId"),
                                    viewModel = viewModel,
                                )
                            }
                        }
                    }
                },
            )



        }

    }

}





@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Mobin App", fontSize = 18.sp) },
        backgroundColor = Color.White,
        contentColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.List,
        NavigationItem.Home,
        NavigationItem.Music,
        NavigationItem.Movies,
        NavigationItem.Books,
        NavigationItem.Profile
    )
    BottomNavigation(
        backgroundColor = Color.Black,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
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
                }
            )
        }
    }
}














