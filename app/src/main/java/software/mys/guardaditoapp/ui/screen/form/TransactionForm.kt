import TransactionForm
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionForm(
    title: @Composable () -> Unit,
    onDismissRequest: () -> Unit = {},
    onSaveClick: (String, String, String, String, String) -> Unit,
    listCategories: List<String>,
    listAccounts: List<String>
) {
    var amount by remember { mutableStateOf("") }
    var account by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var detail by remember { mutableStateOf("") }
    var date by remember {
        mutableStateOf(
            SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            ).format(Date())
        )
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                title()
                Spacer(modifier = Modifier.height(8.dp))

                // Campo Monto
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Monto") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Savings,
                            contentDescription = "Monto"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                // Campo Cuenta con selector
                var showSelectorAccounts by remember { mutableStateOf(false) }
                val availableAccounts = remember {
                    listAccounts
                }

                OutlinedTextField(
                    value = account,
                    onValueChange = { /* No permitir edición directa */ },
                    label = { Text("Cuenta") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccountBalance,
                            contentDescription = "Cuenta"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { showSelectorAccounts = true },
                    shape = RoundedCornerShape(12.dp),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showSelectorAccounts = true }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Seleccionar cuenta"
                            )
                        }
                    }
                )

                // Diálogo de selección de cuentas
                if (showSelectorAccounts) {
                    AlertDialog(
                        onDismissRequest = { showSelectorAccounts = false },
                        title = { Text("Seleccionar Cuenta") },
                        text = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                availableAccounts.forEach { opcionCuenta ->
                                    Surface(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable {
                                                account = opcionCuenta
                                                showSelectorAccounts = false
                                            },
                                        color = if (account == opcionCuenta)
                                            MaterialTheme.colorScheme.primaryContainer
                                        else MaterialTheme.colorScheme.surface
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(12.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Savings,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Text(
                                                text = opcionCuenta,
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }
                                    }
                                }
                            }
                        },
                        confirmButton = {
                            TextButton(onClick = { showSelectorAccounts = false }) {
                                Text("Cerrar")
                            }
                        }
                    )
                }

                // Campo Categoría con selector
                var showCategorySelector by remember { mutableStateOf(false) }
                val availabelCategories = remember {
                    listCategories
                }

                OutlinedTextField(
                    value = category,
                    onValueChange = { /* No permitir edición directa */ },
                    label = { Text("Categoría") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Category,
                            contentDescription = "Categoría"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { showCategorySelector = true },
                    shape = RoundedCornerShape(12.dp),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showCategorySelector = true }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Seleccionar categoría"
                            )
                        }
                    }
                )

                // Diálogo de selección de categorías
                if (showCategorySelector) {
                    AlertDialog(
                        onDismissRequest = { showCategorySelector = false },
                        title = { Text("Seleccionar Categoría") },
                        text = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                availabelCategories.forEach { opcionCategoria ->
                                    Surface(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable {
                                                category = opcionCategoria
                                                showCategorySelector = false
                                            },
                                        color = if (category == opcionCategoria)
                                            MaterialTheme.colorScheme.primaryContainer
                                        else MaterialTheme.colorScheme.surface
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(12.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Category,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Text(
                                                text = opcionCategoria,
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                        }
                                    }
                                }
                            }
                        },
                        confirmButton = {
                            TextButton(onClick = { showCategorySelector = false }) {
                                Text("Cerrar")
                            }
                        }
                    )
                }

                // Campo Detalle
                OutlinedTextField(
                    value = detail,
                    onValueChange = { detail = it },
                    label = { Text("Detalle") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = "Detalle"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                // Campo Fecha con selector de fecha material
                var mostrarSelectorFecha by remember { mutableStateOf(false) }
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                val currentDate = remember {
                    try {
                        dateFormat.parse(date)?.time ?: System.currentTimeMillis()
                    } catch (e: Exception) {
                        System.currentTimeMillis()
                    }
                }

                val datePickerState =
                    rememberDatePickerState(initialSelectedDateMillis = currentDate)

                OutlinedTextField(
                    value = date,
                    onValueChange = { /* No permitir edición directa */ },
                    label = { Text("Fecha") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "Fecha"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { mostrarSelectorFecha = true },
                    shape = RoundedCornerShape(12.dp),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { mostrarSelectorFecha = true }) {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = "Seleccionar fecha"
                            )
                        }
                    }
                )

                if (mostrarSelectorFecha) {
                    DatePickerDialog(
                        onDismissRequest = { mostrarSelectorFecha = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    datePickerState.selectedDateMillis?.let {
                                        date = dateFormat.format(Date(it))
                                    }
                                    mostrarSelectorFecha = false
                                }
                            ) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { mostrarSelectorFecha = false }) {
                                Text("Cancelar")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botones
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Botón Cancelar
                    OutlinedButton(
                        onClick = onDismissRequest,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Cancelar")
                    }

                    // Botón Guardar
                    Button(
                        onClick = {
                            onSaveClick(amount, account, category, detail, date)
                            onDismissRequest()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun TransactionFormPreview() {
    TransactionForm(
        title = {
            Text(
                text = "Titulo",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        },
        onDismissRequest = {},
        onSaveClick = { monto, cuenta, categoria, detalle, fecha ->
        },
        listCategories = listOf(
            "Alimentación",
            "Transporte",
            "Vivienda",
            "Entretenimiento",
            "Salud",
            "Educación",
            "Ropa",
            "Otros"
        ),
        listAccounts = listOf(
            "Efectivo",
            "Tarjeta de Crédito",
            "Cuenta Bancaria",
            "PayPal",
            "Ahorros"
        )
    )
}