package software.mys.guardaditoapp.data.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import software.mys.guardaditoapp.model.Account

interface FireBaseRealTimeService {
    // CREATE: Añadir una cuenta para un usuario
    @POST("users/{userId}/accounts.json")
    fun addAccount(@Path("userId") userId: String, @Body account: Account): Call<Void>
}

