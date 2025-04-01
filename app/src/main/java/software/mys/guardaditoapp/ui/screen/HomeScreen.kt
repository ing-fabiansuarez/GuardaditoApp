package software.mys.guardaditoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import software.mys.guardaditoapp.ui.theme.GuardaditoAppTheme


@Composable
fun HomeScreen() {
    GuardaditoAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar()
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(8.dp)
            ) {
                BalanceCard()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalanceCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
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
                    Text("$50.000,00", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                }
                Icon(Icons.Filled.Visibility, contentDescription = null, tint = Color.Gray)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                BalanceItem(
                    icon = Icons.Default.ArrowUpward,
                    label = "Ingresos",
                    amount = "$0,00",
                    color = Color.Green
                )
                BalanceItem(
                    icon = Icons.Default.ArrowDownward,
                    label = "Gastos",
                    amount = "$0,00",
                    color = Color.Red
                )
                BalanceItem(
                    icon = Icons.Default.Balance,
                    label = "Balance",
                    amount = "$0,00",
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /*TODO*/ },
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

