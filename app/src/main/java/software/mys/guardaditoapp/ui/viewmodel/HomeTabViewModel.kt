package software.mys.guardaditoapp.ui.viewmodel


import android.app.Application
import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.local.entities.TransactionTypeEntity
import software.mys.guardaditoapp.data.local.entities.toUiTransaction
import software.mys.guardaditoapp.data.repositories.StadisticsRepository
import software.mys.guardaditoapp.ui.models.TransactionTypeUi
import software.mys.guardaditoapp.ui.models.TransactionUi
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeTabViewModel(
    application: Application,
    val selectedMonth: Int, // Argumento de Mes
    val selectedYear: Int // Argumento de Año
) : AndroidViewModel(application) {

    val db = AppDatabase.getInstance(application.applicationContext)
    val stadisticsRepository = StadisticsRepository(db.stadisticsDao())

    private val _uiState = MutableStateFlow(
        HomeTabUiState(
            totalBalance = 0.0,
            totalIncome = 0.0,
            totalExpense = 0.0,
            selectedMonth = selectedMonth,
            selectedYear = selectedYear
        )
    )
    val uiState: StateFlow<HomeTabUiState> = _uiState.asStateFlow()

    init {
        refreshHomeTab()
    }

    fun refreshHomeTab() {
        loadTransactions()
        loadTotalBalanceIcomeExpense()
    }


    // Función para cambiar el mes y/o año (ejemplo)
    fun updateSelectedDate(month: Int, year: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedMonth = month,
                selectedYear = year
            )
        }
    }

    private fun loadTotalBalanceIcomeExpense() {
        val month = _uiState.value.selectedMonth
        val year = _uiState.value.selectedYear

        // Construir fechas de inicio y fin del mes en formato yyyy-MM-dd
        val startDate = LocalDate.of(year, month, 1)
        val endDate = startDate.withDayOfMonth(startDate.lengthOfMonth())

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val startDateStr = startDate.format(formatter)
        val endDateStr = endDate.format(formatter)

        _uiState.update { currentState ->
            currentState.copy(
                totalBalance = stadisticsRepository.getTotalBalance(),
                totalIncome = stadisticsRepository.getTotalAmountByType(
                    TransactionTypeEntity.INCOME.name,
                    startDateStr,
                    endDateStr
                ),
                totalExpense = stadisticsRepository.getTotalAmountByType(
                    TransactionTypeEntity.EXPENSE.name,
                    startDateStr,
                    endDateStr
                )
            )
        }
    }

    private fun loadTransactions() {
        val month = _uiState.value.selectedMonth
        val year = _uiState.value.selectedYear
        // Construir fechas de inicio y fin del mes en formato yyyy-MM-dd
        val startDate = LocalDate.of(year, month, 1)
        val endDate = startDate.withDayOfMonth(startDate.lengthOfMonth())

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val startDateStr = startDate.format(formatter)
        val endDateStr = endDate.format(formatter)

        _uiState.update { currentState ->
            currentState.copy(
                monthlyReport = stadisticsRepository.getTransactionsWithDetailsBetweenDates(
                    startDateStr,
                    endDateStr
                ).map {
                    it.toUiTransaction()
                }
            )
        }
        _uiState.update { currentState ->
            currentState.copy(
                monthlyReportByDay = currentState.monthlyReport.groupBy { it.date }
            )
        }
        Log.i("hola", "hola")
    }

    fun getIncomeByMonthlyReport(): BigDecimal =
        _uiState.value.monthlyReport
            .filter { it.type == TransactionTypeUi.INCOME }
            .sumOf { it.amount }

    fun getExpenseByMonthlyReport(): BigDecimal =
        _uiState.value.monthlyReport
            .filter { it.type == TransactionTypeUi.EXPENSE }
            .sumOf { it.amount }

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

    fun updateSelectedMonthAndYear(month: Int, year: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedMonth = month,
                selectedYear = year
            )
        }
        loadTransactions()
        loadTotalBalanceIcomeExpense()
    }

}

data class HomeTabUiState(
    val totalBalance: Double = 0.0,
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val selectedMonth: Int = Calendar.getInstance().get(Calendar.MONTH) + 1, // Mes actual (1-12)
    val selectedYear: Int = Calendar.getInstance().get(Calendar.YEAR),
    val monthlyReport: List<TransactionUi> = emptyList(),
    val monthlyReportByDay: Map<String, List<TransactionUi>> = emptyMap()
)