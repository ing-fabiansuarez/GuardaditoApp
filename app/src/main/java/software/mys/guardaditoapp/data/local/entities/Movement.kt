package software.mys.guardaditoapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "movements")
data class Movement(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Double,
    val type: MovementType, // Enum (INCOME, EXPENSE, TRANSFER)
    val accountId: Long, // FK to Account
    val categoryId: Long?, // FK to Category (optional for transfers)
    val description: String,
    val date: Date,
    val targetAccountId: Long? // Only for transfers
)

enum class MovementType {
    INCOME, EXPENSE, TRANSFER
}