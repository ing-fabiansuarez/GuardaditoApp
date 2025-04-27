package software.mys.guardaditoapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import software.mys.guardaditoapp.ui.models.CategoryUi

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: CategoryEntityType, // INCOME or EXPENSE
)

fun CategoryEntity.toCategoryUi(): CategoryUi {
    return CategoryUi(
        title = name
    )
}

enum class CategoryEntityType {
    INCOME, EXPENSE
}