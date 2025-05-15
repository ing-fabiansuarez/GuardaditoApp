package software.mys.guardaditoapp.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.SET_NULL
    )]
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Double,
    val type: TransactionTypeEntity, // Enum (INCOME, EXPENSE, TRANSFER)
    val accountId: Long, // FK to Account
    val categoryId: Long?, // FK to Category (optional for transfers)
    val description: String,
    val date: String,
    val targetAccountId: Long? // Only for transfers
)

enum class TransactionTypeEntity {
    INCOME, EXPENSE, TRANSFER
}
