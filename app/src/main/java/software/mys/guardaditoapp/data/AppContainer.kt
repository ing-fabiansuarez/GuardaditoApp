package software.mys.guardaditoapp.data

import software.mys.guardaditoapp.data.repository.AccountRepository
import software.mys.guardaditoapp.data.repository.FirebaseRealTimeAccountRepository

interface AppContainer {
    val accountRepository: AccountRepository
}

class DefaultAppContainer : AppContainer {
    override val accountRepository: AccountRepository by lazy {
        FirebaseRealTimeAccountRepository()
    }
}