package software.mys.guardaditoapp.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "movements",
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.SET_NULL
    )]
)
data class MovementEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Double,
    val type: MovementType, // Enum (INCOME, EXPENSE, TRANSFER)
    val accountId: Long, // FK to Account
    val categoryId: Long?, // FK to Category (optional for transfers)
    val description: String,
    val date: String,
    val targetAccountId: Long? // Only for transfers
)

enum class MovementType {
    INCOME, EXPENSE, TRANSFER
}
