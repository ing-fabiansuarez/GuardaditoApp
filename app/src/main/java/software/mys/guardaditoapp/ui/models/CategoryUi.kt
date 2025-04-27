package software.mys.guardaditoapp.ui.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import software.mys.guardaditoapp.data.local.entities.CategoryEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType

data class CategoryUi(
    val title: String,
    var color: Color = Color.Black,
    val type: CategoryUiType = CategoryUiType.EXPENSE,
    var icon: @Composable () -> Unit = {
        Icon(imageVector = Icons.Default.Category, contentDescription = null)
    }
)

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

// Extensión para convertir CategoryUi a CategoryEntity
fun CategoryUi.toEntity(): CategoryEntity {
    return CategoryEntity(
        name = this.title,
        type = when(this.type) {
            CategoryUiType.INCOME -> CategoryEntityType.INCOME
            CategoryUiType.EXPENSE -> CategoryEntityType.EXPENSE
        },
        color = this.color.value.toLong()
    )
}