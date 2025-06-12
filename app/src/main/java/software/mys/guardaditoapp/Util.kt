package software.mys.guardaditoapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Locale
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

fun formatNumberFromDoubleToString(value: Double): String {
    // ... lógica de formateo sin nada de Compose ...
    val symbols = DecimalFormatSymbols(Locale.GERMAN)
    val formatter = DecimalFormat("#,##0.00", symbols)
    return formatter.format(value)
}