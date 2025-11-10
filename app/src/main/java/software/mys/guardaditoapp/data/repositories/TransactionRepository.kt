package software.mys.guardaditoapp.data.repositories


import software.mys.guardaditoapp.data.local.daos.AccountDao
import software.mys.guardaditoapp.data.local.daos.TransactionDao
import software.mys.guardaditoapp.data.local.entities.TransactionEntity
import software.mys.guardaditoapp.data.local.entities.TransactionTypeEntity

class TransactionRepository(
    private val transactionDao: TransactionDao,
    private val accountDao: AccountDao
) {
    fun insertTransaction(transaction: TransactionEntity) {
        //LOGINCA DE AGREGAR UNA TRANSACCION
        //La transaccion se realizo y se debe actualizar el account balance.
        transactionDao.insertMovement(transaction)
        if (transaction.accountId != null) {
            val account = accountDao.getAccountById(transaction.accountId)
            when (transaction.type) {
                TransactionTypeEntity.EXPENSE -> {
                    account.balance -= transaction.amount.toDouble()
                }

                TransactionTypeEntity.INCOME -> {
                    account.balance += transaction.amount.toDouble()
                }

                TransactionTypeEntity.TRANSFER -> {

                }
            }
            accountDao.updateBalance(account)
        }
    }
}