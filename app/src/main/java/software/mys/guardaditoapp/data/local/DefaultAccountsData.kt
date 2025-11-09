package software.mys.guardaditoapp.data.local

import software.mys.guardaditoapp.data.local.entities.AccountEntity

/**
 * Función que genera las cuentas por defecto al inicializar la base de datos.
 */
fun getDefaultAccounts(): List<AccountEntity> {
    return listOf(
        AccountEntity(
            id = 1,
            name = "Efectivo",
            type = "Efectivo",
            balance = 0.0, // Saldo inicial en cero
            color = 0xFF4CAF50L // Verde (Color asociado a efectivo)
        ),
        AccountEntity(
            id = 2,
            name = "Banco Principal",
            type = "Ahorros",
            balance = 0.0, // Saldo inicial en cero
            color = 0xFF2196F3L // Azul (Color asociado a bancos)
        )
    )
}