package software.mys.guardaditoapp.data.repositories

import software.mys.guardaditoapp.data.local.daos.AccountDao
import software.mys.guardaditoapp.data.local.daos.CategoryDao
import software.mys.guardaditoapp.data.local.daos.StadisticsDao

class StadisticsRepository(
    private val stadisticsDao: StadisticsDao
) {
    fun getTotalBalance(): Double {
        return stadisticsDao.getTotalBalance()
    }
}