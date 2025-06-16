package software.mys.guardaditoapp.ui.screen.layout.floating_actions_button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import software.mys.guardaditoapp.R


@Composable
fun HomeFAB(
    onClickTransfer: () -> Unit = {},
    onClickIncome: () -> Unit = {},
    onClickExpense: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.End
    ) {
        if (expanded) {

            // --- Botón 3: Transferencia (Azul) ---
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Transferencia", modifier = Modifier.padding(end = 8.dp))
                FloatingActionButton(
                    onClick = onClickTransfer,
                    containerColor = colorResource(id = R.color.blue_transfer)
                ) {
                    Icon(
                        Icons.Default.SwapHoriz,
                        contentDescription = "Transferencia",
                        tint = Color.White
                    )
                }
            }
            // --- Botón 1: Ingresos (Verde) ---
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Ingresos", modifier = Modifier.padding(end = 8.dp))
                FloatingActionButton(
                    onClick = onClickIncome,
                    containerColor = colorResource(id = R.color.green_income)
                ) {
                    Icon(
                        Icons.Default.AttachMoney,
                        contentDescription = "Ingresos",
                        tint = Color.White
                    )
                }
            }

            // --- Botón 2: Gastos (Rojo) ---
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Gastos", modifier = Modifier.padding(end = 8.dp))
                FloatingActionButton(
                    onClick = onClickExpense,
                    containerColor = colorResource(id = R.color.red_expense)
                ) {
                    Icon(
                        Icons.Default.MoneyOff,
                        contentDescription = "Gastos",
                        tint = Color.White
                    )
                }
            }
        }

        ExtendedFloatingActionButton(
            onClick = { expanded = !expanded },
            icon = {
                Icon(
                    if (expanded) Icons.Filled.Close else Icons.Filled.Add,
                    contentDescription = "Agregar Transacción"
                )
            },
            text = { Text(text = if (expanded) "Cerrar" else "Agregar") },
        )
    }
}