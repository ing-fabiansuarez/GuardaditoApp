package software.mys.guardaditoapp.ui.screen.components.hometab


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import software.mys.guardaditoapp.formatNumberFromDoubleToString
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.models.CategoryUiType
import software.mys.guardaditoapp.ui.models.TransactionTypeUi
import software.mys.guardaditoapp.ui.models.TransactionUi
import java.math.BigDecimal
import kotlin.Long

@Preview(showBackground = true)
@Composable
fun DailyReportCardPreview() {
    val transactions = listOf(
        TransactionUi(
            id = 1,
            amount = BigDecimal("2500.00"),
            type = TransactionTypeUi.INCOME,
            accountId = 1,
            categoryId = 4,
            description = "Pago nómina Mayo 2025",
            date = "2025-05-28"
        ),
        TransactionUi(
            id = 2,
            amount = BigDecimal("85.75"),
            type = TransactionTypeUi.EXPENSE,
            accountId = 3,
            categoryId = 1,
            description = "Compra semanal Walmart",
            date = "2025-06-15"
        ),
        TransactionUi(
            id = 3,
            amount = BigDecimal("500.00"),
            type = TransactionTypeUi.TRANSFER,
            accountId = 1,
            categoryId = 5,
            description = "Ahorro mensual",
            date = "2025-06-01",
            targetAccountId = 2
        ),
        TransactionUi(
            id = 4,
            amount = BigDecimal("35.50"),
            type = TransactionTypeUi.EXPENSE,
            accountId = 1,
            categoryId = 2,
            description = "Gasolina",
            date = "2025-06-10"
        ),
        TransactionUi(
            id = 5,
            amount = BigDecimal("1200.00"),
            type = TransactionTypeUi.INCOME,
            accountId = 2,
            categoryId = 4,
            description = "Proyecto diseño app",
            date = "2025-05-20"
        )
    )

    DailyReportCard(
        day = "01",
        month = "Abril",
        year = "2025",
        dayName = "Martes",
        income = "$1.621.812,00",
        expenses = "$1.409.200,00",
        transactions = transactions,
        expandeded = true
    )
}

@Composable
fun DailyReportCard(
    day: String,
    dayName: String,
    month: String,
    year: String,
    income: String,
    expenses: String,
    transactions: List<TransactionUi> = emptyList(),
    expandeded: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }
    expanded = expandeded
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )

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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = day,
                        fontSize = 30.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = dayName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = "$month-$year", fontSize = 14.sp, color = Color.Gray)
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (!expanded) {
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = income,
                                color = Color(0xFF00994C),
                            )
                            Text(
                                text = expenses,
                                color = Color(0xFFCC0000),
                            )
                        }
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
                    Text("Ingresos")
                    Text(income, color = Color(0xFF00994C))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Gastos")
                    Text(expenses, color = Color(0xFFCC0000))
                }
            }
        }
    }

}

@Composable
fun TransactionItem(transaction: TransactionUi) {
    var category = transaction.categoryUi
    if (category == null) {
        category = CategoryUi(
            id = 0,
            name = "Otra",
            color = 0xFFCC0000,
            type = CategoryUiType.INCOME,
            icon = Icons.Default.Category
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .padding(4.dp)
                .background(Color(category.color), CircleShape)
        ) {
            Icon(category.icon, null)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            val text = ""

            Text(
                "${category.name} ${if (!transaction.description.isEmpty()) " * ${transaction.description}" else ""}",
            )
            val accountName = if (transaction.accountUi != null) {
                transaction.accountUi.name
            } else {
                "No tiene Cuenta"
            }
            Text(accountName, fontSize = 12.sp, color = Color.Gray)
        }
        Text(
            text = "$ ${formatNumberFromDoubleToString(transaction.amount)}",
            color = if (transaction.type == TransactionTypeUi.EXPENSE) Color(0xFFCC0000) else Color(
                0xFF00994C
            ),
        )
    }
}
