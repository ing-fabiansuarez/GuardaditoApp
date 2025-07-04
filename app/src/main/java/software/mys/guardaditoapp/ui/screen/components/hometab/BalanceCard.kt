package software.mys.guardaditoapp.ui.screen.components.hometab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import software.mys.guardaditoapp.formatNumberFromDoubleToString
import java.math.BigDecimal


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalanceCard(
    saldoTotal: Double = 0.0,
    incomeTotal: BigDecimal = BigDecimal(0.0),
    expenseTotal: BigDecimal = BigDecimal(0.0),
    onAccountClick: () -> Unit = {},
    mounth: String,
    year: String
) {
    var isBalanceVisible by rememberSaveable { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Saldo total", color = Color.Gray)
                    Text(
                        if (isBalanceVisible) "$ ${formatNumberFromDoubleToString(saldoTotal)}" else "$ ****.**",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Icon(
                    imageVector = if (isBalanceVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (isBalanceVisible) "Ocultar saldo" else "Mostrar saldo",
                    modifier = Modifier.clickable {
                        // Cambia el estado de visibilidad al hacer clic
                        isBalanceVisible = !isBalanceVisible
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Icon(Icons.Default.CalendarToday, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("$mounth $year", fontSize = 18.sp)
                }
                BalanceItem(
                    icon = Icons.Default.ArrowUpward,
                    label = "Ingresos",
                    amount = "$ ${formatNumberFromDoubleToString(incomeTotal)}",
                    color = Color.Green
                )
                BalanceItem(
                    icon = Icons.Default.ArrowDownward,
                    label = "Gastos",
                    amount = "$ ${formatNumberFromDoubleToString(expenseTotal)}",
                    color = Color.Red
                )
                BalanceItem(
                    icon = Icons.Default.Balance,
                    label = "Balance",
                    amount = "$ ${formatNumberFromDoubleToString(incomeTotal - expenseTotal)}",
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onAccountClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50)
            ) {
                Icon(Icons.Filled.AccountBalanceWallet, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Gestionar cuentas")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalanceItem(icon: ImageVector, label: String, amount: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = color)
            Spacer(modifier = Modifier.width(8.dp))
            Text(label)
        }
        Text(amount, color = color)
    }
}