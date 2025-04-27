package software.mys.guardaditoapp.ui.models

import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Icon

import androidx.compose.ui.graphics.Color
import software.mys.guardaditoapp.data.local.entities.CategoryEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.vector.ImageVector

// Lista de iconos disponibles
val availableIcons = listOf(
    Icons.Default.Home,
    Icons.Default.ShoppingCart,
    Icons.Default.Restaurant,
    Icons.Default.DirectionsCar,
    Icons.Default.AttachMoney,
    Icons.Default.HealthAndSafety,
    Icons.Default.School,
    Icons.Default.ChildCare,
    Icons.Default.Star,
    Icons.Default.Work
)

data class CategoryUi(
    val title: String,
    val color: Color = Color.Black,
    val type: CategoryUiType = CategoryUiType.EXPENSE,
    val icon: ImageVector = Icons.Default.Category
) {
    fun toEntity(): CategoryEntity {
        return CategoryEntity(
            name = this.title,
            type = when(this.type) {
                CategoryUiType.INCOME -> CategoryEntityType.INCOME
                CategoryUiType.EXPENSE -> CategoryEntityType.EXPENSE
            },
            color = this.color.value.toLong(),
            iconName = icon.name // Guardamos el nombre del icono
        )
    }
}

fun CategoryEntity.toCategoryUi(): CategoryUi {
    return CategoryUi(
        title = this.name,
        color = Color(this.color),
        type = when(this.type) {
            CategoryEntityType.INCOME -> CategoryUiType.INCOME
            CategoryEntityType.EXPENSE -> CategoryUiType.EXPENSE
        },
        icon = availableIcons.find { it.name == iconName } ?: Icons.Default.Category
    )
}


// Definimos un enum para los tipos de categorías.
enum class CategoryUiType {
    INCOME, EXPENSE;

    // Función para obtener el nombre de la pestaña
    fun getTabName(): String {
        return when (this) {
            INCOME -> "Ingresos"
            EXPENSE -> "Gastos"
        }
    }
}