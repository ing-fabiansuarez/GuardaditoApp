package software.mys.guardaditoapp.ui.models

import androidx.compose.material.icons.filled.Category

import software.mys.guardaditoapp.data.local.entities.CategoryEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType
import androidx.compose.material.icons.Icons

import androidx.compose.ui.graphics.vector.ImageVector
import software.mys.guardaditoapp.ui.util.AppIcons


data class CategoryUi(
    val id: Long = 0,
    val name: String = "",
    val color: Long = 0xFF2196F3,
    val type: CategoryUiType = CategoryUiType.INCOME,
    val icon: ImageVector = Icons.Default.Category
)

fun CategoryUi.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = this.id,
        name = this.name,
        type = when (this.type) {
            CategoryUiType.INCOME -> CategoryEntityType.INCOME
            CategoryUiType.EXPENSE -> CategoryEntityType.EXPENSE
        },
        color = this.color,
        iconName = AppIcons.getNameByIcon(icon) // Guardamos el nombre del icono
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