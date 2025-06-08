package software.mys.guardaditoapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import software.mys.guardaditoapp.ui.models.AccountUi
import java.text.NumberFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(onBackClick: () -> Unit = {}) {
    // Datos de ejemplo para la lista
    val sampleAccounts = remember {
        List(10) { index ->
            val colorVerde = 0xFF4CAF50L
            val colorAzul = 0xFF2196F3L
            val colorNaranja = 0xFFFF9800L
            val colorRosa = 0xFFE91E63L
            AccountUi(
                id = index.toLong(),
                name = "Cuenta Ejemplo ${index + 1}",
                type = if (index % 2 == 0) "Ahorros" else "Corriente",
                balance = (100000..5000000).random().toDouble(),
                colorHex = when (index % 4) { // Cambiado aquí
                    0 -> colorVerde
                    1 -> colorAzul
                    2 -> colorNaranja
                    else -> colorRosa
                }
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Accounts") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    OutlinedButton(
                        onClick = { }
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "Agregar",
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(16.dp), // Padding alrededor de la lista
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre items
        ) {
            item { // Primer item especial para el SummaryAccount
                SummaryAccount(total = sampleAccounts.sumOf { it.balance })
                Spacer(modifier = Modifier.size(16.dp)) // Espacio después del resumen
            }

            items(
                sampleAccounts,
                key = { account -> account.id }) { account -> // Bucle sobre la lista de cuentas
                AccountItem(account = account)
            }
        }

    }

}


@Composable
fun SummaryAccount(total: Double) {
    var isBalanceVisible by rememberSaveable { mutableStateOf(true) }
    // Obtener el contexto local para acceder a la configuración regional del dispositivo
    val context = LocalContext.current
    // Obtener la configuración regional actual del dispositivo
    val currentLocale = context.resources.configuration.locales[0]

    // Crear un formateador de números para la configuración regional actual
    // Esto asegura que se usen los separadores correctos (p.ej., "," para miles y "." para decimal en US)
    // o (p.ej., "." para miles y "," para decimal en muchos países europeos)
    val numberFormatter = remember(currentLocale) {
        NumberFormat.getInstance(currentLocale).apply {
            // Opcional: Configurar el número máximo y mínimo de dígitos fraccionarios
            // minimumFractionDigits = 2
            // maximumFractionDigits = 2
        }
    }
    val formattedBalance = remember(total, currentLocale) {
        numberFormatter.format(total)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Saldo Total")
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Muestra el saldo o asteriscos según el estado de isBalanceVisible
            Text(
                text = if (isBalanceVisible) "$ $formattedBalance" else "$ ****.**",
                fontSize = 32.sp
            )
            Icon(
                imageVector = if (isBalanceVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                contentDescription = if (isBalanceVisible) "Ocultar saldo" else "Mostrar saldo",
                modifier = Modifier.clickable {
                    // Cambia el estado de visibilidad al hacer clic
                    isBalanceVisible = !isBalanceVisible
                }
            )
        }
    }
}

@Composable
fun AccountItem(account: AccountUi) {
    Card {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.AccountBalanceWallet,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = Color(account.colorHex)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(account.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(account.type)
                    }
                }
                Icon(Icons.AutoMirrored.Filled.NavigateNext, contentDescription = null)
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Balance: ")
                Text(
                    "$ ${account.balance.toString()}",
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview() // Añade un fondo blanco para mejor visualización
@Composable
fun Preview() {
    //SummaryAccount(1000000.215)
    AccountScreen()
}
