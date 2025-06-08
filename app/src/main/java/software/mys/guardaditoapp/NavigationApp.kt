package software.mys.guardaditoapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import software.mys.guardaditoapp.ui.screen.AccountScreen
import software.mys.guardaditoapp.ui.screen.MainScreen

@Composable
fun NavigationApp() {
    val context = LocalContext.current
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Main.route) {
        composable(Routes.Main.route) {
            MainScreen(
                onAccountClick = { navController.navigate(Routes.Accounts.route) }
            )
        }
        composable(Routes.Accounts.route) {
            AccountScreen(onBackClick = { navController.popBackStack() })
        }
    }
}