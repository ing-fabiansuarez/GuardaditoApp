package software.mys.guardaditoapp.ui.screen

import TransactionForm
import android.app.Application
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import software.mys.guardaditoapp.ui.screen.components.NavigationBottomAppBar
import software.mys.guardaditoapp.Routes
import software.mys.guardaditoapp.ui.models.CategoryUiType
import software.mys.guardaditoapp.ui.models.TransactionType
import software.mys.guardaditoapp.ui.screen.components.floating_actions_button.CategoryFAB
import software.mys.guardaditoapp.ui.screen.components.floating_actions_button.HomeFAB
import software.mys.guardaditoapp.ui.screen.form.CategoryForm
import software.mys.guardaditoapp.ui.screen.tabs.CategoriesTab
import software.mys.guardaditoapp.ui.screen.tabs.HomeTab
import software.mys.guardaditoapp.ui.theme.GuardaditoAppTheme
import software.mys.guardaditoapp.ui.viewmodel.CategoryViewModel
import software.mys.guardaditoapp.ui.viewmodel.CategoryViewModelFactory
import software.mys.guardaditoapp.ui.viewmodel.TransactionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val application = LocalContext.current.applicationContext as Application

    val categoryViewmodel: CategoryViewModel =
        viewModel(factory = CategoryViewModelFactory(application))
    val transactionViewModel: TransactionViewModel = viewModel()

    var showCategoryForm by remember { mutableStateOf(false) }
    var showTransactionForm by remember { mutableStateOf(false) }
    var typeTransaction by remember { mutableStateOf(TransactionType.INCOME) }

    if (showCategoryForm) {
        CategoryForm(onCloseClick = {
            showCategoryForm = false
        }, onSaveComplete = { category ->
            categoryViewmodel.addNewCategory(category)
            showCategoryForm = false
        })
    }
    if (showTransactionForm) {
        when (typeTransaction) {
            TransactionType.INCOME -> {
                TransactionForm(
                    title = {
                        Row(
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Savings,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = Color(0xFF4CAF50)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "Nuevo Ingreso",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    onSaveClick = { a, b, cd, d, e ->

                    },
                    listCategories = transactionViewModel.categories.filter {
                        it.type == CategoryUiType.INCOME
                    }.map { it.name },
                    listAccounts = transactionViewModel.accounts.map { it.name },
                    onDismissRequest = {
                        showTransactionForm = false
                    }
                )
            }

            TransactionType.EXPENSE -> {
                TransactionForm(
                    title = {
                        Row(
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoneyOff,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = Color(0xFFF44336)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "Nuevo Gasto",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    onSaveClick = { a, b, cd, d, e ->

                    },
                    listCategories = listOf(
                        "Alimentación",
                        "Transporte",
                        "Vivienda",
                        "Entretenimiento"
                    ),
                    listAccounts = listOf(
                        "Efectivo",
                        "Tarjeta"
                    ), onDismissRequest = {
                        showTransactionForm = false
                    }
                )
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

        },
        floatingActionButton = {
            when (currentDestination?.route) {
                Routes.HomeTab.route -> HomeFAB(
                    onClickIncome = {
                        typeTransaction = TransactionType.INCOME
                        showTransactionForm = true
                    },
                    onClickExpense = {
                        typeTransaction = TransactionType.EXPENSE
                        showTransactionForm = true
                    }
                )

                Routes.CategoriesTab.route -> CategoryFAB(onClickListener = {
                    showCategoryForm = true
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





