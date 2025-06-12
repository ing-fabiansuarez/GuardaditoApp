package software.mys.guardaditoapp.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import java.text.NumberFormat

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