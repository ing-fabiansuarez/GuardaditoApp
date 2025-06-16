package software.mys.guardaditoapp.ui.screen.components.hometab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DailyReportCard(
    date: String,
    dayName: String,
    income: String,
    expenses: String,
    transactions: List<Transaction> = emptyList()
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            // .background(Color(0xFFF4F2F7), RoundedCornerShape(16.dp))

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = dayName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = date, fontSize = 14.sp, color = Color.Gray)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = income, color = Color(0xFF00994C), fontWeight = FontWeight.Bold)
                    Text(text = expenses, color = Color(0xFFCC0000), fontWeight = FontWeight.Bold)
                }
                IconButton(onClick = {
                    expanded = !expanded
                }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null
                    )
                }

            }

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                transactions.forEach {
                    TransactionItem(transaction = it)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Ingresos", fontWeight = FontWeight.Bold)
                    Text(income, color = Color(0xFF00994C), fontWeight = FontWeight.Bold)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Gastos", fontWeight = FontWeight.Bold)
                    Text(expenses, color = Color(0xFFCC0000), fontWeight = FontWeight.Bold)
                }
            }
        }
    }

}

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(transaction.iconColor, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(transaction.title, fontWeight = FontWeight.Medium)
            Text(transaction.account, fontSize = 12.sp, color = Color.Gray)
        }
        Text(
            text = transaction.amount,
            color = if (transaction.amount.startsWith("$-")) Color(0xFFCC0000) else Color(0xFF00994C),
            fontWeight = FontWeight.Bold
        )
    }
}

data class Transaction(
    val title: String,
    val account: String,
    val amount: String,
    val iconColor: Color
)

@Preview(showBackground = true)
@Composable
fun DailyReportCardPreview() {
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
