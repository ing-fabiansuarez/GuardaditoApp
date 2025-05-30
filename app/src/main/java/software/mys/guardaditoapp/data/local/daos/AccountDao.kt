package software.mys.guardaditoapp.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import software.mys.guardaditoapp.data.local.entities.AccountEntity


@Dao
interface AccountDao {
    @Insert
    fun insert(account: AccountEntity): Long

    @Query("SELECT * FROM accounts ORDER BY name ASC")
    fun getAll(): List<AccountEntity>

    @Update
    fun updateBalance(account: AccountEntity)

    @Query("SELECT * FROM accounts WHERE id = :accountId")
    fun getAccountById(accountId: Long): AccountEntity

}