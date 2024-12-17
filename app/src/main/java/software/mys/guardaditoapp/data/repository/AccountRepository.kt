package software.mys.guardaditoapp.data.repository

import software.mys.guardaditoapp.data.network.FireBaseRealTimeService

interface AccountRepository {
    suspend fun addAccount():String
}

class DefaultAccountRepository(
    private val accountRealTimeService: FireBaseRealTimeService
) : AccountRepository {
    override suspend fun addAccount(): String {
        TODO("Not yet implemented")
    }
}

