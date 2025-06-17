package software.mys.guardaditoapp.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import software.mys.guardaditoapp.data.local.entities.TransactionEntity
import software.mys.guardaditoapp.data.local.entities.TransactionTypeEntity
import software.mys.guardaditoapp.data.local.entities.TransactionWithRelations

@Dao
interface StadisticsDao {
    @Query("SELECT SUM(balance) FROM accounts")
    fun getTotalBalance(): Double

    @Query("SELECT SUM(amount) FROM transactions WHERE type = :transactionType")
    fun getTotalAmountByType(transactionType: String): Double

    @Query("SELECT SUM(amount) FROM transactions WHERE type = :transactionType BETWEEN :startDate AND :endDate ORDER BY date ASC")
    fun getTotalAmountByType(transactionType: String, startDate: String, endDate: String): Double

    @Query("SELECT * FROM transactions WHERE date BETWEEN :startDate AND :endDate ORDER BY date ASC")
    fun getTransactionsBetweenDates(startDate: String, endDate: String): List<TransactionEntity>

    @Transaction
    @Query("SELECT * FROM transactions WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getTransactionsWithRelations(startDate: String, endDate: String): List<TransactionWithRelations>
}