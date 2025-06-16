package software.mys.guardaditoapp.ui.screen.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import software.mys.guardaditoapp.ui.viewmodel.HomeTabViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import software.mys.guardaditoapp.ui.screen.components.hometab.BalanceCard
import software.mys.guardaditoapp.ui.screen.components.hometab.DailyReportCard
import software.mys.guardaditoapp.ui.screen.components.hometab.MounthSummary
import software.mys.guardaditoapp.ui.screen.components.hometab.Transaction

@Composable
fun HomeTab(onAccountClick: () -> Unit = {}) {
    val viewModel: HomeTabViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        BalanceCard(
            saldoTotal = uiState.totalBalance,
            onAccountClick = onAccountClick,
            incomeTotal = uiState.totalIncome,
            expenseTotal = uiState.totalExpense,
            mounth = viewModel.getMonthName(),
            year = uiState.selectedYear.toString()
        )
        MounthSummary(viewModel.getMonthName(), uiState.selectedYear.toString())

        val sampleTransactions = listOf(
            Transaction("Salario", "Mi banco", "$1.621.812,00", Color(0xFF00796B)),
            Transaction("Pamplona Mercado", "Mi banco", "$350.000,00", Color(0xFF558B2F)),
            Transaction("Arriendo", "Mi banco", "$759.200,00", Color(0xFFFBC02D)),
            Transaction("Carro - Parqueadero Unab", "Mi banco", "$140.000,00", Color(0xFF303F9F)),
            Transaction("Carro - Parqueadero Casa", "Mi banco", "$160.000,00", Color(0xFF303F9F))
        )

        DailyReportCard(
            date = "01 Abr. 2025",
            dayName = "Martes",
            income = "$1.621.812,00",
            expenses = "$1.409.200,00",
            transactions = sampleTransactions
        )
    }
}
