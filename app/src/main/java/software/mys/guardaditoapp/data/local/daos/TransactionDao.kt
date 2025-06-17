package software.mys.guardaditoapp.data.local.daos

import androidx.room.*
import software.mys.guardaditoapp.data.local.entities.TransactionEntity

@Dao
interface TransactionDao {
    @Insert
    fun insertMovement(transaction: TransactionEntity): Long

    @Query("SELECT * FROM transactions WHERE date LIKE :month || '%'")
    suspend fun getTransactionsByMonth(month: String): List<TransactionEntity>
}