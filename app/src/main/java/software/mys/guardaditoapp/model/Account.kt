package software.mys.guardaditoapp.model

// Modelo para una cuenta
data class Account(
    val id: String,
    val name: String,
    val balance: Double,
    val type: String,
    val incomes: List<Income>,
    val expenses: List<Expense>
)

data class Income(
    val amount: Double,
    val date: String,
    val category: String
)

data class Expense(
    val amount: Double,
    val date: String,
    val category: String
)