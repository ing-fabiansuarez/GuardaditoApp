package software.mys.guardaditoapp.ui.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Category
import androidx.compose.ui.graphics.vector.ImageVector
import software.mys.guardaditoapp.data.local.entities.AccountEntity
import software.mys.guardaditoapp.ui.util.AppIcons

data class AccountUi(
    val id: Long = 0,             // ID único (útil para Firebase u otra base de datos)
    val name: String = "",           // Nombre de la cuenta, ej. "Nequi", "Davivienda"
    val type: String = "",           // Tipo: "Ahorros", "Corriente", "Crédito", "Efectivo"
    val balance: Double = 0.0,        // Saldo visible (como String si viene del usuario o editable)
    val colorHex: Long = 0xFF00897B // Color asociado (puede usarse en UI para distinguir cuentas)
)

fun AccountUi.toEntity(): AccountEntity {
    return AccountEntity(
        id = id,
        name = name,
        type = type,
        balance = balance,
        color = colorHex
    )
}