package software.mys.guardaditoapp.data.repositories

import software.mys.guardaditoapp.data.local.daos.StadisticsDao
import software.mys.guardaditoapp.data.local.entities.TransactionEntity
import software.mys.guardaditoapp.data.local.entities.TransactionWithRelations

class StadisticsRepository(
    private val stadisticsDao: StadisticsDao
) {
    fun getTotalBalance(): Double {
        return stadisticsDao.getTotalBalance()
    }

    fun getTotalAmountByType(
        type: String,
        startDate: String? = null,
        endDate: String? = null
    ): Double {
        if (startDate != null && endDate != null) {
            return stadisticsDao.getTotalAmountByType(type, startDate, endDate)
        } else {
            return stadisticsDao.getTotalAmountByType(type)
        }
    }


    fun getTransactionsBetweenDates(startDate: String, endDate: String): List<TransactionEntity> {
        return stadisticsDao.getTransactionsBetweenDates(startDate, endDate)
    }

    fun getTransactionsWithDetailsBetweenDates(
        startDate: String,
        endDate: String
    ): List<TransactionWithRelations> {
        return stadisticsDao.getTransactionsWithRelations(startDate, endDate)
    }
}