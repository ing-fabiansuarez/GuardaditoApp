package software.mys.guardaditoapp.data

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import software.mys.guardaditoapp.data.network.FireBaseRealTimeService
import software.mys.guardaditoapp.data.repository.AccountRepository
import software.mys.guardaditoapp.data.repository.DefaultAccountRepository


interface AppContainer {
    val accountRepository: AccountRepository
}

class DefaultAppContainer : AppContainer {

    private val BASE_URL_REAL_TIME_DB = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL_REAL_TIME_DB)
        .build()

    private val retrofitService: FireBaseRealTimeService by lazy {
        retrofit.create(FireBaseRealTimeService::class.java)
    }

    override val accountRepository: AccountRepository by lazy {
        DefaultAccountRepository(retrofitService)
    }
}