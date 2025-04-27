package software.mys.guardaditoapp.ui.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class CategoryUi(
    val title: String,
    var color: Color = Color.Black,
    val type: CategoryType = CategoryType.EXPENSE,
    var icon: @Composable () -> Unit = {
        Icon(imageVector = Icons.Default.Category, contentDescription = null)
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