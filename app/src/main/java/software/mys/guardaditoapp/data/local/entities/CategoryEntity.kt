package software.mys.guardaditoapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.models.CategoryUiType
import software.mys.guardaditoapp.ui.util.AppIcons

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: CategoryEntityType,
    val color: Long, // Color en formato ARGB
    val iconName: String // Nuevo campo para el icono
)

// Extensión mejorada para convertir CategoryEntity a CategoryUi
fun CategoryEntity.toCategoryUi(): CategoryUi {
    return CategoryUi(
        id = this.id,
        name = this.name,
        color = this.color,
        type = when(this.type) {
            CategoryEntityType.INCOME -> CategoryUiType.INCOME
            CategoryEntityType.EXPENSE -> CategoryUiType.EXPENSE
        },
        icon = AppIcons.getIconByName(this.iconName)
    )
}
enum class CategoryEntityType {
    INCOME, EXPENSE
}