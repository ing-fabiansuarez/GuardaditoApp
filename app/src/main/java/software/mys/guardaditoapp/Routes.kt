package software.mys.guardaditoapp

sealed class Routes(val route: String, val title: String) {
    object Main : Routes("main", "Inicio")
    object HomeTab : Routes("home", "Inicio")
    object CategoriesTab : Routes("categories", "Categorías")
    object CategoryForm : Routes("category-form", "Formulario de Categoría")
}