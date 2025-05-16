package software.mys.guardaditoapp.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelectorDialog(
    date: String,
    supportingText: @Composable () -> Unit,
    isError: Boolean,
    onDateSelected: (String) -> Unit,

) {
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
        },
        supportingText = supportingText,
        isError = isError,
    )

    if (mostrarSelectorFecha) {
        DatePickerDialog(
            onDismissRequest = { mostrarSelectorFecha = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            onDateSelected(dateFormat.format(Date(it)))
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
}