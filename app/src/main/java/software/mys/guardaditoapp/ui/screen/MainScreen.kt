package software.mys.guardaditoapp.ui.screen

import TransactionDialog
import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import software.mys.guardaditoapp.ui.screen.components.NavigationBottomAppBar
import software.mys.guardaditoapp.Routes
import software.mys.guardaditoapp.ui.screen.components.floating_actions_button.CategoryFAB
import software.mys.guardaditoapp.ui.screen.components.floating_actions_button.HomeFAB
import software.mys.guardaditoapp.ui.screen.tabs.CategoriesTab
import software.mys.guardaditoapp.ui.screen.tabs.HomeTab
import software.mys.guardaditoapp.ui.theme.GuardaditoAppTheme
import software.mys.guardaditoapp.ui.viewmodel.CategoryViewModel
import software.mys.guardaditoapp.ui.viewmodel.CategoryViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    GuardaditoAppTheme {
        val navController = rememberNavController()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val application = LocalContext.current.applicationContext as Application
        val categoryViewmodel: CategoryViewModel =
            viewModel(factory = CategoryViewModelFactory(application))

        var showModal by remember { mutableStateOf(false) }


        if (showModal) {
            ModalBottomSheet(
                onDismissRequest = { showModal = false }
            ) {
                CategoryFormScreen(onCloseClick = {
                    showModal = false
                }, onSaveComplete = { category ->
                    categoryViewmodel.addNewCategory(category)
                    showModal = false
                })

            }
        }

        TransactionDialog(
            onDismissRequest = { },
            onSaveClick = { monto, cuenta, categoria, detalle, fecha ->
                // Aquí puedes hacer lo que necesites con los datos capturados
                println("Monto: $monto")
                println("Cuenta: $cuenta")
                println("Categoría: $categoria")
                println("Detalle: $detalle")
                println("Fecha: $fecha")
            }
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {

            },
            floatingActionButton = {
                when (currentDestination?.route) {
                    Routes.HomeTab.route -> HomeFAB()
                    Routes.CategoriesTab.route -> CategoryFAB(onClickListener = {
                        showModal = true
                    })
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





