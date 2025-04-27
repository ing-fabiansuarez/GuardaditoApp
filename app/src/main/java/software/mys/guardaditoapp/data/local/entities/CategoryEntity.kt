package software.mys.guardaditoapp.data.local.entities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.models.CategoryUiType

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: CategoryEntityType, // INCOME or EXPENSE
    val color: Long // Color en formato ARGB
)

// Extensión mejorada para convertir CategoryEntity a CategoryUi
fun CategoryEntity.toCategoryUi(): CategoryUi {
    return CategoryUi(
        title = this.name,
        color = Color(this.color),
        type = when(this.type) {
            CategoryEntityType.INCOME -> CategoryUiType.INCOME
            CategoryEntityType.EXPENSE -> CategoryUiType.EXPENSE
        },
        icon = {
            val iconVector = when(this.type) {
                CategoryEntityType.INCOME -> Icons.Default.ArrowUpward
                CategoryEntityType.EXPENSE -> Icons.Default.ArrowDownward
            }
            Icon(imageVector = iconVector, contentDescription = null)
        }
    )
}
enum class CategoryEntityType {
    INCOME, EXPENSE
}