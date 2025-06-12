package software.mys.guardaditoapp.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import software.mys.guardaditoapp.data.local.entities.AccountEntity


@Dao
interface AccountDao {
    @Insert
    fun insert(account: AccountEntity): Long

    @Update
    fun update(account: AccountEntity)

    @Query("SELECT * FROM accounts ORDER BY id ASC")
    fun getAll(): List<AccountEntity>

    @Update
    fun updateBalance(account: AccountEntity)

    @Query("SELECT * FROM accounts WHERE id = :accountId")
    fun getAccountById(accountId: Long): AccountEntity

    @Delete
    fun delete(account: AccountEntity)
}