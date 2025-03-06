package software.mys.guardaditoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun CategoryScreen() {
    Scaffold(
        topBar = { TopApp() },
        content = { innerPadding ->
            TabsExpenseAndIncome(modifier = Modifier.padding(innerPadding))
        },
        floatingActionButton = {
            FloatingButton()
        }
    )
}

@Composable
fun FloatingButton() {
    FloatingActionButton(
        onClick = {  },
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}

@Composable
fun TabsExpenseAndIncome(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        var selectedTabIndex by remember { mutableStateOf(0) }

        // Usamos el enum para definir las pestañas
        val tabs = CategoryType.entries.toTypedArray()

        Column(modifier = Modifier.fillMaxSize()) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, categoryType ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = categoryType.getTabName()) }
                    )
                }
            }

            when (tabs[selectedTabIndex]) {
                CategoryType.INCOME -> {
                    incomeCategories.forEach { category ->
                        CategoryItem(category)
                    }
                }

                CategoryType.EXPENSE -> {
                    expenseCategories.forEach { category ->
                        CategoryItem(category)
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopApp() {
    TopAppBar(
        title = {
            Text("Categories", fontWeight = FontWeight.Bold)
        }
    )
}

@Composable
fun CategoryItem(category: Category, onDelete: () -> Unit = {}) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { }
                    .background(color = category.color) // Asigna el color aquí
                ,
                contentAlignment = Alignment.Center
            ) {
                category.icon()
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = category.title, fontSize = 16.sp, fontWeight = FontWeight.Medium)

        }
        // Ícono de eliminar
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete, // Cambia esto por el ícono que desees usar
                contentDescription = "Eliminar categoría"
            )
        }
    }
    HorizontalDivider()
}


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


// Definimos un enum para los tipos de categorías.
enum class CategoryType {
    INCOME, EXPENSE;

    // Función para obtener el nombre de la pestaña
    fun getTabName(): String {
        return when (this) {
            INCOME -> "Ingresos"
            EXPENSE -> "Gastos"
        }
    }
}

data class Category(
    val title: String,
    val color: Color = Color.Black,
    val type: CategoryType,
    val icon: @Composable () -> Unit
)