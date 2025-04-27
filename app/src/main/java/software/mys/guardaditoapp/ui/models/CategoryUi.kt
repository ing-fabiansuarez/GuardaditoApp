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


data class CategoryUi(
    val title: String,
    val color: Long = 0xFF2196F3,
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
            color = this.color,
            iconName = icon.name // Guardamos el nombre del icono
        )
    }
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