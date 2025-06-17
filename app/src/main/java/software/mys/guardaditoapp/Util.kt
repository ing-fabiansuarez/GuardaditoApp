package software.mys.guardaditoapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.math.BigDecimal
import java.util.Locale
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun formatNumberFromDoubleToString(value: Double): String =
    DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale.GERMAN)).format(value)
fun formatNumberFromDoubleToString(value: BigDecimal): String =
    DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale.GERMAN)).format(value)

//la fecha debe ser en el formato "yyyy-MM-dd"
fun getDayOfWeekFromDate(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(dateString, formatter)
    return date.dayOfWeek.toString() // Devuelve el nombre en inglés (MONDAY, TUESDAY, etc.)
}

//la fecha debe ser en el formato "yyyy-MM-dd"
fun getDayOfWeekInSpanish(dateString: String): String {
    val days = mapOf(
        DayOfWeek.MONDAY to "Lunes",
        DayOfWeek.TUESDAY to "Martes",
        DayOfWeek.WEDNESDAY to "Miércoles",
        DayOfWeek.THURSDAY to "Jueves",
        DayOfWeek.FRIDAY to "Viernes",
        DayOfWeek.SATURDAY to "Sábado",
        DayOfWeek.SUNDAY to "Domingo"
    )
    val date = LocalDate.parse(dateString)
    return days[date.dayOfWeek] ?: "Día desconocido"
}

fun getMonthNameSpanish(month: Int): String {
    // Esto es básico, considera usar `java.time.Month` o `SimpleDateFormat` para localización
    return when (month) {
        1 -> "Enero"
        2 -> "Febrero"
        3 -> "Marzo"
        4 -> "Abril"
        5 -> "Mayo"
        6 -> "Junio"
        7 -> "Julio"
        8 -> "Agosto"
        9 -> "Septiembre"
        10 -> "Octubre"
        11 -> "Noviembre"
        12 -> "Diciembre"
        else -> ""
    }
}