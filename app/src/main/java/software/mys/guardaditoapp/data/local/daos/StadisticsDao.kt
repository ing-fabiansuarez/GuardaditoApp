package software.mys.guardaditoapp.data.local.daos

import androidx.room.Dao
import androidx.room.Query

@Dao
interface StadisticsDao {
    @Query("SELECT SUM(balance) FROM accounts")
    fun getTotalBalance(): Double
}