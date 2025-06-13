package software.mys.guardaditoapp

sealed class Routes(val route: String, val title: String) {
    object Main : Routes("main", "Inicio")
    object HomeTab : Routes("home", "Inicio")
    object CategoriesTab : Routes("categories", "Categorías")
    object Accounts : Routes("accounts", "Cuentas")
    object FormTransactions : Routes("form-transactions/{transactionType}", "Forumulario Transacciones") {
        fun createRoute(transactionType: String) = "form-transactions/$transactionType"
    }
}