package software.mys.guardaditoapp.model

// Modelo para una cuenta
data class Account(
    val id: String = "",
    val name: String = "",
    val balance: Double = 0.0,
    val type: String = ""
)