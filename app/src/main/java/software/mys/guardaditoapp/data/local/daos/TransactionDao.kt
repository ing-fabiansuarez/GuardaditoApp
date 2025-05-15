package software.mys.guardaditoapp.data.local.dao

import androidx.room.*
import software.mys.guardaditoapp.data.local.entities.TransactionEntity

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovement(transaction: TransactionEntity): Long
}