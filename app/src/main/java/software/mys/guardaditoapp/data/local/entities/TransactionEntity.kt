package software.mys.guardaditoapp.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import software.mys.guardaditoapp.ui.models.TransactionTypeUi
import software.mys.guardaditoapp.ui.models.TransactionUi
import java.math.BigDecimal

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["targetAccountId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: BigDecimal = BigDecimal("0.0"),
    val type: TransactionTypeEntity,
    val accountId: Long?, // <- ahora nullable
    val categoryId: Long?,
    val description: String = "",
    val date: String = "",
    val targetAccountId: Long? = null
)

fun TransactionEntity.toUiModel(): TransactionUi {
    return TransactionUi(
        id = this.id,
        amount = this.amount,
        type = this.type.toUiModel(),
        accountId = this.accountId ?: 0,
        categoryId = this.categoryId ?: 0,
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
        TransactionTypeEntity.TRANSFER -> TransactionTypeUi.TRANSFER
    }
}
