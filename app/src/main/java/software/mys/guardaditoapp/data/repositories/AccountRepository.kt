package software.mys.guardaditoapp.data.repositories

import software.mys.guardaditoapp.data.local.daos.AccountDao
import software.mys.guardaditoapp.data.local.entities.AccountEntity

class AccountRepository(private val accountDao: AccountDao) {
    fun save(account: AccountEntity) {
        if (account.id == 0L) {
            accountDao.insert(account)
        }else{
            accountDao.update(account)
        }
    }
    fun getAllAccounts(): List<AccountEntity> {
        return accountDao.getAll()
    }
    fun delete(account: AccountEntity) {
        accountDao.delete(account)
    } 
}