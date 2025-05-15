package software.mys.guardaditoapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import software.mys.guardaditoapp.ui.models.AccountUi

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String = "",               // Nombre de la cuenta, ej. "Nequi", "Davivienda"
    val type: String = "",           // Tipo: "Ahorros", "Corriente", "Crédito", "Efectivo"
    val balance: Double = 0.0,        // Saldo visible (como String si viene del usuario o editable)
    val icon: String = "",           // Nombre del ícono asociado o URL/local path
    val color: Long = 0xFFFFFFF
)

fun AccountEntity.toUi(): AccountUi {
    return AccountUi(
        id = id,
        name = name,
        type = type,
        balance = balance,
        icon = icon,
        colorHex = String.format("#%06X", 0xFFFFFF and color.toInt())
    )
}