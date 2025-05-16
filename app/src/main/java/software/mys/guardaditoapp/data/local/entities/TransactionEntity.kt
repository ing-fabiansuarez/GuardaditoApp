package software.mys.guardaditoapp.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import software.mys.guardaditoapp.ui.models.TransactionTypeUi
import software.mys.guardaditoapp.ui.models.TransactionUi

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

fun TransactionEntity.toUiModel(): TransactionUi {
    return TransactionUi(
        id = this.id,
        amount = this.amount,
        type = this.type.toUiModel(),
        accountId = this.accountId,
        categoryId = this.categoryId,
        description = this.description,
        date = this.date,
        targetAccountId = this.targetAccountId
    )
}


enum class TransactionTypeEntity {
    INCOME, EXPENSE, TRANSFER
}

fun TransactionTypeEntity.toUiModel(): TransactionTypeUi {
    return when (this) {
        TransactionTypeEntity.INCOME -> TransactionTypeUi.INCOME
        TransactionTypeEntity.EXPENSE -> TransactionTypeUi.EXPENSE
        TransactionTypeEntity.TRANSFER -> TransactionTypeUi.EXPENSE
    }
}
