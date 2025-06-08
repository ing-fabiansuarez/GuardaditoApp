package software.mys.guardaditoapp.data.local.entities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Category
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import software.mys.guardaditoapp.ui.models.AccountUi
import software.mys.guardaditoapp.ui.util.AppIcons

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String = "",               // Nombre de la cuenta, ej. "Nequi", "Davivienda"
    val type: String = "",           // Tipo: "Ahorros", "Corriente", "Crédito", "Efectivo"
    var balance: Double = 0.0,        // Saldo visible (como String si viene del usuario o editable)
    val iconName: String = "Default.Business",     // Nombre del ícono asociado o URL/local path
    val color: Long = 0xFFFFFFF
)

fun AccountEntity.toUi(): AccountUi {
    return AccountUi(
        id = id,
        name = name,
        type = type,
        balance = balance,
        icon = AppIcons.getIconByName(this.iconName),
        colorHex = color
    )
}