package software.mys.guardaditoapp.ui.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import software.mys.guardaditoapp.ui.screen.Category
import software.mys.guardaditoapp.ui.screen.CategoryType

class CategoryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(
            incomeCategories = incomeCategories,
            expenseCategories = expenseCategories
        )
    }

}

data class CategoryUiState(
    val incomeCategories: List<Category> = emptyList(),
    val expenseCategories: List<Category> = emptyList()
)


val expenseCategories = listOf(
    // Categorías de gastos
    Category("Alimentos", color = Color(0xFFFFA000), type = CategoryType.EXPENSE) { // Naranja
        Icon(Icons.Filled.FoodBank, contentDescription = null)
    },
    Category("Facturas", color = Color(0xFF6A1B9A), type = CategoryType.EXPENSE) { // Púrpura
        Icon(Icons.Filled.Description, contentDescription = null)
    },
    Category("Transporte", color = Color(0xFF0288D1), type = CategoryType.EXPENSE) { // Azul
        Icon(Icons.Filled.DirectionsBus, contentDescription = null)
    },
    Category("Compras", color = Color(0xFFE91E63), type = CategoryType.EXPENSE) { // Rosa
        Icon(Icons.Filled.ShoppingCart, contentDescription = null)
    },
    Category("Regalos", color = Color(0xFFE91E63), type = CategoryType.EXPENSE) { // Rosa
        Icon(Icons.Filled.CardGiftcard, contentDescription = null)
    },
    Category(
        "Educación",
        color = Color(0xFF00796B),
        type = CategoryType.EXPENSE
    ) { // Verde oscuro
        Icon(Icons.Filled.School, contentDescription = null)
    },
    Category("Renta", color = Color(0xFFF57C00), type = CategoryType.EXPENSE) { // Naranja
        Icon(Icons.Filled.Home, contentDescription = null)
    },
    Category("Viajes", color = Color(0xFFE91E63), type = CategoryType.EXPENSE) { // Rosa
        Icon(Icons.Filled.AirplanemodeActive, contentDescription = null)
    },

    )

val incomeCategories = listOf(
    // Nuevas categorías de ingresos
    Category("Salario", color = Color(0xFF4CAF50), type = CategoryType.INCOME) { // Verde
        Icon(Icons.Filled.Money, contentDescription = null)
    },
    Category("Inversiones", color = Color(0xFF3F51B5), type = CategoryType.INCOME) { // Azul
        Icon(Icons.Filled.AccountBalance, contentDescription = null)
    }
)

