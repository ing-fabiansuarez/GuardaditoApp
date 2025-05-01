import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDialog(
    onDismissRequest: () -> Unit,
    onSaveClick: (String, String, String, String, String) -> Unit
) {
    var monto by remember { mutableStateOf("") }
    var cuenta by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var detalle by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())) }

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
                // Título del diálogo
                Text(
                    text = "Nuevo Registro",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Campo Monto
                OutlinedTextField(
                    value = monto,
                    onValueChange = { monto = it },
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
                var mostrarSelectorCuentas by remember { mutableStateOf(false) }
                val cuentasDisponibles = remember {
                    listOf("Efectivo", "Tarjeta de Crédito", "Cuenta Bancaria", "PayPal", "Ahorros")
                }

                OutlinedTextField(
                    value = cuenta,
                    onValueChange = { /* No permitir edición directa */ },
                    label = { Text("Cuenta") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Savings,
                            contentDescription = "Cuenta"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { mostrarSelectorCuentas = true },
                    shape = RoundedCornerShape(12.dp),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { mostrarSelectorCuentas = true }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Seleccionar cuenta"
                            )
                        }
                    }
                )

                // Diálogo de selección de cuentas
                if (mostrarSelectorCuentas) {
                    AlertDialog(
                        onDismissRequest = { mostrarSelectorCuentas = false },
                        title = { Text("Seleccionar Cuenta") },
                        text = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                cuentasDisponibles.forEach { opcionCuenta ->
                                    Surface(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable {
                                                cuenta = opcionCuenta
                                                mostrarSelectorCuentas = false
                                            },
                                        color = if (cuenta == opcionCuenta)
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
                            TextButton(onClick = { mostrarSelectorCuentas = false }) {
                                Text("Cerrar")
                            }
                        }
                    )
                }

                // Campo Categoría con selector
                var mostrarSelectorCategorias by remember { mutableStateOf(false) }
                val categoriasDisponibles = remember {
                    listOf("Alimentación", "Transporte", "Vivienda", "Entretenimiento", "Salud", "Educación", "Ropa", "Otros")
                }

                OutlinedTextField(
                    value = categoria,
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
                        .clickable { mostrarSelectorCategorias = true },
                    shape = RoundedCornerShape(12.dp),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { mostrarSelectorCategorias = true }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Seleccionar categoría"
                            )
                        }
                    }
                )

                // Diálogo de selección de categorías
                if (mostrarSelectorCategorias) {
                    AlertDialog(
                        onDismissRequest = { mostrarSelectorCategorias = false },
                        title = { Text("Seleccionar Categoría") },
                        text = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                categoriasDisponibles.forEach { opcionCategoria ->
                                    Surface(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable {
                                                categoria = opcionCategoria
                                                mostrarSelectorCategorias = false
                                            },
                                        color = if (categoria == opcionCategoria)
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
                            TextButton(onClick = { mostrarSelectorCategorias = false }) {
                                Text("Cerrar")
                            }
                        }
                    )
                }

                // Campo Detalle
                OutlinedTextField(
                    value = detalle,
                    onValueChange = { detalle = it },
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

                // Campo Fecha
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "Fecha"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botones
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Botón Cancelar
                    Button(
                        onClick = onDismissRequest,
                        modifier = Modifier.weight(1f).padding(end = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Cancelar")
                    }

                    // Botón Guardar
                    Button(
                        onClick = {
                            onSaveClick(monto, cuenta, categoria, detalle, fecha)
                            onDismissRequest()
                        },
                        modifier = Modifier.weight(1f).padding(start = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}

// Ejemplo de cómo usar el diálogo en tu aplicación
@Composable
fun MiPantallaConDialogo() {
    var mostrarDialogo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { mostrarDialogo = true }) {
            Text("Mostrar Diálogo")
        }

        if (mostrarDialogo) {
            TransactionDialog(
                onDismissRequest = { mostrarDialogo = false },
                onSaveClick = { monto, cuenta, categoria, detalle, fecha ->
                    // Aquí puedes hacer lo que necesites con los datos capturados
                    println("Monto: $monto")
                    println("Cuenta: $cuenta")
                    println("Categoría: $categoria")
                    println("Detalle: $detalle")
                    println("Fecha: $fecha")
                }
            )
        }
    }
}