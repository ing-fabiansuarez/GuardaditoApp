package software.mys.guardaditoapp.ui.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.repositories.StadisticsRepository

class HomeTabViewModel(application: Application) : AndroidViewModel(application) {

    val db = AppDatabase.getInstance(application.applicationContext)
    val stadisticsRepository = StadisticsRepository(db.stadisticsDao())

    private val _uiState = MutableStateFlow(HomeTabUiState())
    val uiState: StateFlow<HomeTabUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = uiState.value.copy(totalBalance = loadTotalBalance())
    }


    fun loadTotalBalance(): Double {
        return stadisticsRepository.getTotalBalance()
    }

}

data class HomeTabUiState(
    val totalBalance: Double = 0.0,
)