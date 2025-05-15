package software.mys.guardaditoapp.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.local.daos.AccountDao
import software.mys.guardaditoapp.data.local.daos.CategoryDao
import software.mys.guardaditoapp.data.local.entities.AccountEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType

class AccountRepository(private val accountDao: AccountDao) {
    fun insert(account: AccountEntity) {
        accountDao.insert(account)
    }
    fun getAllAccounts(): List<AccountEntity> {
        return accountDao.getAll()
    }
}