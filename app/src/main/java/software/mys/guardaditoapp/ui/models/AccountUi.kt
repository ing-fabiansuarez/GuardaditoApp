package software.mys.guardaditoapp.ui.models

import software.mys.guardaditoapp.data.local.entities.AccountEntity

data class AccountUi(
    val id: Long = 0,             // ID único (útil para Firebase u otra base de datos)
    val name: String = "",           // Nombre de la cuenta, ej. "Nequi", "Davivienda"
    val type: String = "",           // Tipo: "Ahorros", "Corriente", "Crédito", "Efectivo"
    val balance: Double = 0.0,        // Saldo visible (como String si viene del usuario o editable)
    val icon: String = "",           // Nombre del ícono asociado o URL/local path
    val colorHex: String = "#FFFFFF" // Color asociado (puede usarse en UI para distinguir cuentas)
)

fun AccountUi.toEntity(): AccountEntity {
    return AccountEntity(
        id = id,
        name = name,
        type = type,
        balance = balance,
        icon = icon,
        color = colorHex.removePrefix("#").toLong(16)
    )
}