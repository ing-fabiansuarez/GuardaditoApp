package software.mys.guardaditoapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import software.mys.guardaditoapp.ui.models.TransactionTypeUi
import software.mys.guardaditoapp.ui.screen.components.floating_actions_button.CategoryFAB
import software.mys.guardaditoapp.ui.screen.components.floating_actions_button.HomeFAB
import software.mys.guardaditoapp.ui.screen.form.CategoryForm
import software.mys.guardaditoapp.ui.screen.tabs.CategoriesTab
import software.mys.guardaditoapp.ui.screen.tabs.HomeTab
import software.mys.guardaditoapp.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onAccountClick: () -> Unit = {}, onTransactionClick: (TransactionTypeUi) -> Unit = {}
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val context = LocalContext.current

    val mainViewModel: MainViewModel = viewModel()
    val uiState by mainViewModel.uiState.collectAsState()

    var showCategoryForm by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(uiState.categoryFormUi) }

    if (showCategoryForm) {
        CategoryForm(onCloseClick = {
            showCategoryForm = false
        }, onSaveComplete = { category ->
            mainViewModel.addNewCategory(category)
            Toast.makeText(context, "Categoría guardada", Toast.LENGTH_SHORT).show()
            showCategoryForm = false
        }, category = selectedCategory)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

        }, floatingActionButton = {
            when (currentDestination?.route) {
                Routes.HomeTab.route -> HomeFAB(onClickIncome = {
                    onTransactionClick(TransactionTypeUi.INCOME)
                }, onClickExpense = {
                    onTransactionClick(TransactionTypeUi.EXPENSE)
                })

                Routes.CategoriesTab.route -> {
                    CategoryFAB(onClickListener = {
                        showCategoryForm = true
                    })
                }
            }
        }, bottomBar = { NavigationBottomAppBar(navController) }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HomeTab.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Routes.HomeTab.route) {
                HomeTab(
                    onAccountClick = { onAccountClick() })
            }
            composable(route = Routes.CategoriesTab.route) {
                mainViewModel.loadAllCategories()
                CategoriesTab(listCategories = uiState.listCategories, onDeleteCategory = {
                    mainViewModel.deleteCategory(it)
                    Toast.makeText(context, "Categoría eliminada", Toast.LENGTH_SHORT).show()
                }, onClickItem = {
                    selectedCategory = it
                    showCategoryForm = true
                })
            }
        }
    }
}






