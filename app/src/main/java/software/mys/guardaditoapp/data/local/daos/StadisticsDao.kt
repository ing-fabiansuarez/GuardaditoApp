package software.mys.guardaditoapp.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import software.mys.guardaditoapp.data.local.entities.TransactionTypeEntity

@Dao
interface StadisticsDao {
    @Query("SELECT SUM(balance) FROM accounts")
    fun getTotalBalance(): Double

    @Query("SELECT SUM(amount) FROM transactions WHERE type = :transactionType")
    fun getTotalAmountByType(transactionType: String): Double
}