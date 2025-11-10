package software.mys.guardaditoapp.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import software.mys.guardaditoapp.ui.models.TransactionUi

data class TransactionWithRelations(
    @Embedded val transaction: TransactionEntity,
    @Relation(
        parentColumn = "accountId",
        entityColumn = "id"
    )
    val account: AccountEntity?,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: CategoryEntity?
)

fun TransactionWithRelations.toUiTransaction(): TransactionUi {
    return TransactionUi(
        id = this.transaction.id,
        amount = this.transaction.amount,
        type = this.transaction.type.toUiModel(),
        accountId = this.transaction.accountId,
        categoryId = this.transaction.categoryId,
        description = this.transaction.description,
        date = this.transaction.date,
        targetAccountId = this.transaction.targetAccountId,
        accountUi = this.account?.toUi(),
        categoryUi = this.category?.toCategoryUi()
    )
}