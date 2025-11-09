package software.mys.guardaditoapp.ui.screen

import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import software.mys.guardaditoapp.ui.screen.layout.NavigationBottomAppBar
import software.mys.guardaditoapp.Routes
import software.mys.guardaditoapp.getMonthNameSpanish
import software.mys.guardaditoapp.ui.models.TransactionTypeUi
import software.mys.guardaditoapp.ui.screen.components.MonthYearPickerDialog
import software.mys.guardaditoapp.ui.screen.layout.floating_actions_button.CategoryFAB
import software.mys.guardaditoapp.ui.screen.layout.floating_actions_button.HomeFAB
import software.mys.guardaditoapp.ui.screen.form.CategoryForm
import software.mys.guardaditoapp.ui.screen.tabs.CategoriesTab
import software.mys.guardaditoapp.ui.screen.tabs.HomeTab
import software.mys.guardaditoapp.ui.viewmodel.MainViewModel
import java.time.Month

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onAccountClick: () -> Unit = {}, onTransactionClick: (TransactionTypeUi) -> Unit = {},
    refreshHomeTrigger: Boolean
) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val context = LocalContext.current

    val mainViewModel: MainViewModel = viewModel()
    val uiState by mainViewModel.uiState.collectAsState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    //Esto es para la pantalla de formulario de categorías
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

    var showMonthPicker by remember { mutableStateOf(false) }

    // var showMonthPicker by remember { mutableStateOf(false) }
    if (showMonthPicker) {
        MonthYearPickerDialog(
            year = uiState.selectedYear,
            month = Month.of(uiState.selectedMonth),
            onDismissRequest = {
                showMonthPicker = false
            },
            onDateSelected = { year, month ->
                mainViewModel.updateSelectedDate(month.value, year)
                showMonthPicker = false
            }
        )
    }


    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        if (scrollBehavior.state.collapsedFraction < 0.5f) "Bienvenido" else "Transacciones",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {

                },
                actions = {
                    Button(onClick = {
                        showMonthPicker = true
                    }) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Menu")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "${getMonthNameSpanish(uiState.selectedMonth)} ${uiState.selectedYear}")
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.ExpandMore, contentDescription = "Menu")
                    }
                },
                scrollBehavior = scrollBehavior
            )

        }, floatingActionButton = {
            when (currentDestination?.route) {
                Routes.HomeTab.route -> {
                    HomeFAB(onClickIncome = {
                        onTransactionClick(TransactionTypeUi.INCOME)
                    }, onClickExpense = {
                        onTransactionClick(TransactionTypeUi.EXPENSE)
                    }, onClick = {

                    })


                }

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
                    onAccountClick = onAccountClick,
                    refreshTrigger = refreshHomeTrigger,
                    showMonthPicker = showMonthPicker,
                    selectedMonth = uiState.selectedMonth,
                    selectedYear = uiState.selectedYear,
                )
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








