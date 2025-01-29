package software.mys.guardaditoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import software.mys.guardaditoapp.data.network.FireBaseRealTimeApiService
import software.mys.guardaditoapp.data.network.MyApi
import software.mys.guardaditoapp.model.Account



class HomeViewModel : ViewModel() {

    val api: FireBaseRealTimeApiService = MyApi.retrofitService

    private val _uiState = MutableStateFlow(emptyList<Account>())
    val uiState: StateFlow<List<Account>> = _uiState.asStateFlow()

    init {
        loadAccountsUser() // Se ejecuta automáticamente al crear el ViewModel
    }


    fun loadAccountsUser() {
        api.getAccountUser(userId = "GxyHMRiihmOxLQl7LbivetfEUDh1").enqueue(object :
            Callback<List<Account>> {
            override fun onResponse(call: Call<List<Account>>, response: Response<List<Account>>) {
                if (response.isSuccessful) {
                    val accounts = response.body()
                    accounts?.let {
                        _uiState.value = it  // Actualiza el estado con los datos obtenidos
                        println("Datos actualizados: ${it}")
                    }
                } else {
                    println("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }
}