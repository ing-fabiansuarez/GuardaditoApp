package software.mys.guardaditoapp.ui.screen.tabs

import android.app.Application
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import software.mys.guardaditoapp.ui.viewmodel.HomeTabViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import software.mys.guardaditoapp.formatNumberFromDoubleToString
import software.mys.guardaditoapp.getDayOfWeekInSpanish
import software.mys.guardaditoapp.getMonthNameSpanish
import software.mys.guardaditoapp.ui.models.TransactionTypeUi
import software.mys.guardaditoapp.ui.models.TransactionUi
import software.mys.guardaditoapp.ui.screen.components.MonthYearPickerDialog
import software.mys.guardaditoapp.ui.screen.components.hometab.BalanceCard
import software.mys.guardaditoapp.ui.screen.components.hometab.DailyReportCard
import software.mys.guardaditoapp.ui.screen.components.hometab.MounthSummary
import java.time.Month

@Composable
fun HomeTab(
    modifier:Modifier = Modifier,
    onAccountClick: () -> Unit = {},
    refreshTrigger: Boolean,
    showMonthPicker: Boolean = false,
    selectedMonth: Int = Calendar.getInstance().get(Calendar.MONTH) + 1,
    selectedYear: Int = Calendar.getInstance().get(Calendar.YEAR),
) {

    val application: Application = LocalContext.current.applicationContext as Application
    val viewModel: HomeTabViewModel = HomeTabViewModel(
        application,
        selectedMonth,
        selectedYear
    )
    val uiState by viewModel.uiState.collectAsState()
    var expandedAll by remember { mutableStateOf(false) }


    //Lo que hace esta funcion es que si la variable refreshTrigger cambia, se llama a la funcion refreshHomeTab()
    LaunchedEffect(refreshTrigger) {
        viewModel.refreshHomeTab()
    }

    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState()) // Esto añade el scroll vertical
    ) {
        BalanceCard(
            saldoTotal = uiState.totalBalance,
            onAccountClick = onAccountClick,
            incomeTotal = viewModel.getIncomeByMonthlyReport(),
            expenseTotal = viewModel.getExpenseByMonthlyReport(),
            mounth = viewModel.getMonthName(),
            year = uiState.selectedYear.toString(),
        )
        MounthSummary(
            viewModel.getMonthName(), uiState.selectedYear.toString(),
            onExpandClick = {
                expandedAll = it
            }
        )


        uiState.monthlyReportByDay.forEach { (date, transactions: List<TransactionUi>) ->
            // Split the date string into year, month, day
            val parts = date.split("-")
            val year = parts.getOrNull(0) ?: "0000"
            val month = parts.getOrNull(1) ?: "00"
            val day = parts.getOrNull(2) ?: "00"

            val totalExpenses = transactions
                .filter { it.type == TransactionTypeUi.EXPENSE }
                .sumOf { it.amount }
            val totalIncome =
                transactions
                    .filter { it.type == TransactionTypeUi.INCOME }
                    .sumOf { it.amount }

            DailyReportCard(
                dayName = getDayOfWeekInSpanish(date),
                income = "$ ${formatNumberFromDoubleToString(totalIncome)}",
                expenses = "$ ${formatNumberFromDoubleToString(totalExpenses)}",
                transactions = transactions,
                day = day,
                month = getMonthNameSpanish(month.toInt()),
                year = year,
                expandeded = expandedAll
            )
        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}
