package software.mys.guardaditoapp

import android.os.Bundle

import androidx.activity.compose.setContent

import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import software.mys.guardaditoapp.ui.screen.CategoryScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import software.mys.guardaditoapp.ui.screen.CategoryFAB
import software.mys.guardaditoapp.ui.screen.HomeFAB

import software.mys.guardaditoapp.ui.screen.HomeScreen
import software.mys.guardaditoapp.ui.theme.GuardaditoAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuardaditoAppTheme {
                val navController = rememberNavController()
                AppScaffold(navController)
            }
        }
    }
}

@Composable
fun AppScaffold(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {

        },
        floatingActionButton = {
            when(currentDestination?.route) {
                Routes.Home.route -> HomeFAB()
                Routes.Categories.route -> CategoryFAB()
            }
        },
        bottomBar = { NavigationBottomAppBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Routes.Home.route) {
                HomeScreen()
            }
            composable(route = Routes.Categories.route) {
                CategoryScreen()
            }
        }
    }

}



