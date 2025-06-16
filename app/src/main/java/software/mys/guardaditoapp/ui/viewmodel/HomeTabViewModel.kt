package software.mys.guardaditoapp.ui.viewmodel


import android.app.Application
import android.icu.util.Calendar
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.local.entities.TransactionTypeEntity
import software.mys.guardaditoapp.data.repositories.StadisticsRepository

class HomeTabViewModel(application: Application) : AndroidViewModel(application) {

    val db = AppDatabase.getInstance(application.applicationContext)
    val stadisticsRepository = StadisticsRepository(db.stadisticsDao())

    private val _uiState = MutableStateFlow(
        HomeTabUiState(
            totalBalance = stadisticsRepository.getTotalBalance(),
            totalIncome = stadisticsRepository.getTotalAmountByType(TransactionTypeEntity.INCOME.name),
            totalExpense = stadisticsRepository.getTotalAmountByType(TransactionTypeEntity.EXPENSE.name)
        )
    )
    val uiState: StateFlow<HomeTabUiState> = _uiState.asStateFlow()


    // Función para cambiar el mes y/o año (ejemplo)
    fun updateSelectedDate(month: Int, year: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedMonth = month,
                selectedYear = year
            )
        }
    }

    fun getMonthName(): String {
        // Esto es básico, considera usar `java.time.Month` o `SimpleDateFormat` para localización
        return when (_uiState.value.selectedMonth) {
            1 -> "Enero"
            2 -> "Febrero"
            3 -> "Marzo"
            4 -> "Abril"
            5 -> "Mayo"
            6 -> "Junio"
            7 -> "Julio"
            8 -> "Agosto"
            9 -> "Septiembre"
            10 -> "Octubre"
            11 -> "Noviembre"
            12 -> "Diciembre"
            else -> ""
        }
    }

}

data class HomeTabUiState(
    val totalBalance: Double = 0.0,
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val selectedMonth: Int = Calendar.getInstance().get(Calendar.MONTH) + 1, // Mes actual (1-12)
    val selectedYear: Int = Calendar.getInstance().get(Calendar.YEAR)
)