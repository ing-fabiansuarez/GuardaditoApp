package software.mys.guardaditoapp.data.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import software.mys.guardaditoapp.model.Account


private const val BASE_URL =
    "https://guardaditoapp-7eeb1-default-rtdb.firebaseio.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object MyApi {
    val retrofitService: FireBaseRealTimeApiService by lazy {
        retrofit.create(FireBaseRealTimeApiService::class.java)
    }
}

interface FireBaseRealTimeApiService {
    // CREATE: Añadir una cuenta para un usuario
    @POST("users/{userId}/accounts.json")
    fun addAccounts(@Path("userId") userId: String, @Body accounts: List<Account>): Call<Void>

    @GET("/users/{userId}/accounts.json")
    fun getAccountUser(@Path("userId") userId: String): Call<List<Account>>

}

