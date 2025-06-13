package software.mys.guardaditoapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import software.mys.guardaditoapp.ui.models.TransactionTypeUi
import software.mys.guardaditoapp.ui.screen.AccountScreen
import software.mys.guardaditoapp.ui.screen.MainScreen
import software.mys.guardaditoapp.ui.screen.TransactionFormScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
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
                }
            )
        }
        composable(Routes.Accounts.route) {
            AccountScreen(
                onBackClick = { navController.popBackStack() }
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
                onSaveClick = { navController.popBackStack() }
            )
        }
    }
}