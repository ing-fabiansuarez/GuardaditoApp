package software.mys.guardaditoapp.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.Month
import java.time.format.TextStyle
import java.util.*


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import java.time.Year

@Preview
@Composable
fun MonthYearPickerDialogPreview() {
    var selectedMonth by remember { mutableStateOf(Month.JANUARY) }
    MonthYearPickerDialog(
        initialYear = 2023,
        initialMonth = selectedMonth,
        onDismissRequest = {},
        onDateSelected = { year, month ->

        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthYearPickerDialog(
    initialYear: Int = Year.now().value,
    initialMonth: Month = Month.JANUARY,
    onDismissRequest: () -> Unit,
    onDateSelected: (year: Int, month: Month) -> Unit
) {
    var selectedYear by remember { mutableStateOf(initialYear) }
    var selectedMonth by remember { mutableStateOf(initialMonth) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Select Month and Year") },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Year selector row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { selectedYear-- }) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Previous Year"
                        )
                    }
                    Text(
                        text = selectedYear.toString(),
                        style = MaterialTheme.typography.titleLarge
                    )
                    IconButton(onClick = { selectedYear++ }) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Next Year"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Months grid 3 columns x 4 rows
                val months = Month.entries.toTypedArray()
                val chunkedMonths = months.toList().chunked(3)
                Column {
                    chunkedMonths.forEach { rowMonths ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            rowMonths.forEach { month ->
                                val isSelected = month == selectedMonth
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(2f)
                                        .clickable { selectedMonth = month }
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = month.name.take(3).capitalize(),
                                            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onDateSelected(selectedYear, selectedMonth) }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    )
}
