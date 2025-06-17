package software.mys.guardaditoapp.data.local

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.Date

class BigDecimalConverters {
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toPlainString()
    }

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }
}
