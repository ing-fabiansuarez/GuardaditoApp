package software.mys.guardaditoapp

sealed class Routes(val route: String, val title: String) {
    object Home : Routes("home", "Inicio")
    object Categories : Routes("categories", "Categorías")
}