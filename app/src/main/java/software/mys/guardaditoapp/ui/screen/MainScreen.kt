package software.mys.guardaditoapp.ui.screen

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import software.mys.guardaditoapp.ui.screen.components.NavigationBottomAppBar
import software.mys.guardaditoapp.Routes
import software.mys.guardaditoapp.ui.screen.tabs.CategoriesTab
import software.mys.guardaditoapp.ui.screen.tabs.CategoryFAB
import software.mys.guardaditoapp.ui.screen.tabs.HomeFAB
import software.mys.guardaditoapp.ui.screen.tabs.HomeTab
import software.mys.guardaditoapp.ui.theme.GuardaditoAppTheme
import software.mys.guardaditoapp.ui.viewmodel.CategoryViewModel
import software.mys.guardaditoapp.ui.viewmodel.CategoryViewModelFactory

@Composable
fun MainScreen() {
    GuardaditoAppTheme {
        val navController = rememberNavController()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val application = LocalContext.current.applicationContext as Application
        val categoryViewmodel: CategoryViewModel = viewModel(factory = CategoryViewModelFactory(application))

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {

            },
            floatingActionButton = {
                when (currentDestination?.route) {
                    Routes.HomeTab.route -> HomeFAB()
                    Routes.CategoriesTab.route -> CategoryFAB(categoryViewmodel)
                }
            },
            bottomBar = { NavigationBottomAppBar(navController) }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Routes.HomeTab.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = Routes.HomeTab.route) {
                    HomeTab()
                }
                composable(route = Routes.CategoriesTab.route) {
                    CategoriesTab(categoryViewmodel)
                }
            }
        }

    }
}





