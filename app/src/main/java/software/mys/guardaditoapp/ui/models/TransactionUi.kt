package software.mys.guardaditoapp.ui.models

import software.mys.guardaditoapp.data.local.entities.TransactionEntity
import software.mys.guardaditoapp.data.local.entities.TransactionTypeEntity

data class TransactionUi(
    val id: Long = 0,
    val amount: Double,
    val type: TransactionTypeUi, // Enum (INCOME, EXPENSE, TRANSFER)
    val accountId: Long, // FK to Account
    val categoryId: Long?, // FK to Category (optional for transfers)
    val description: String,
    val date: String,
    val targetAccountId: Long? // Only for transfers
)

fun TransactionUi.toEntityModel(): TransactionEntity {
    return TransactionEntity(
        id = this.id,
        amount = this.amount,
        type = this.type.toEntityModel(),
        accountId = this.accountId,
        categoryId = this.categoryId,
        description = this.description,
        date = this.date,
        targetAccountId = this.targetAccountId
    )
}

enum class TransactionTypeUi {
    INCOME,
    EXPENSE,
}

fun TransactionTypeUi.toEntityModel(): TransactionTypeEntity {
    return when (this) {
        TransactionTypeUi.INCOME -> TransactionTypeEntity.INCOME
        TransactionTypeUi.EXPENSE -> TransactionTypeEntity.EXPENSE
    }
}