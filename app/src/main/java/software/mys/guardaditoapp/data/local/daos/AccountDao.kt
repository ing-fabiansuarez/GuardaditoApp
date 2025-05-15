package software.mys.guardaditoapp.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import software.mys.guardaditoapp.data.local.entities.AccountEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType


@Dao
interface AccountDao {
    @Insert
    fun insert(account: AccountEntity): Long

    @Query("SELECT * FROM accounts ORDER BY name ASC")
    fun getAll(): List<AccountEntity>
}