package software.mys.guardaditoapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import okhttp3.Route
import software.mys.guardaditoapp.ui.models.TransactionTypeUi
import software.mys.guardaditoapp.ui.screen.AccountScreen
import software.mys.guardaditoapp.ui.screen.MainScreen
import software.mys.guardaditoapp.ui.screen.ReportsScreen
import software.mys.guardaditoapp.ui.screen.SettingsScreen
import software.mys.guardaditoapp.ui.screen.TransactionFormScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    //la idea de esta variable es que se refresque el home si se agrega una nueva transaccion
    var refreshHomeTrigger by remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = Routes.Main.route) {
        composable(Routes.Main.route) {
            MainScreen(
                onAccountClick = { navController.navigate(Routes.Accounts.route) },
                onTransactionClick = { typeTransaction ->
                    navController.navigate(
                        Routes.FormTransactions.createRoute(
                            typeTransaction.name
                        )
                    )
                },
                onSettingClick = {

                    navController.navigate(Routes.Settings.route)

                },
                refreshHomeTrigger = refreshHomeTrigger,
            )
        }
        composable(Routes.Accounts.route) {
            AccountScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Routes.Settings.route) {
            SettingsScreen(
                onNavigateBack={
                    navController.popBackStack()
                }
            )
        }

        composable(
            Routes.FormTransactions.route,
            arguments = listOf(
                navArgument("transactionType") { type = NavType.StringType }
            )) { backStackEntry ->
            val transactionType = backStackEntry.arguments?.getString("transactionType")
                ?.let { TransactionTypeUi.valueOf(it) } ?: TransactionTypeUi.INCOME
            TransactionFormScreen(
                transactionType = transactionType,
                onBackClick = { navController.popBackStack() },
                onSaveClick = {
                    refreshHomeTrigger = !refreshHomeTrigger
                    navController.popBackStack()
                }
            )
        }
    }
}