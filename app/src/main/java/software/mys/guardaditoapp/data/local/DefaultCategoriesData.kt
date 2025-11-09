package software.mys.guardaditoapp.data.local


import software.mys.guardaditoapp.data.local.entities.CategoryEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.util.AppIcons // Asegúrate que esta clase exista

// Colores comunes en formato ARGB (Long)
const val COLOR_GASTO_ROJO = 0xFFFF5757L // Rojo
const val COLOR_GASTO_NARANJA = 0xFFFF9800L // Naranja
const val COLOR_GASTO_AZUL = 0xFF2196F3L // Azul
const val COLOR_GASTO_MORADO = 0xFF9C27B0L // Morado

const val COLOR_INGRESO_VERDE = 0xFF4CAF50L // Verde

/**
 * Función que genera la lista de categorías por defecto para la base de datos.
 * Debe ser llamada al inicializar la base de datos (ej. en un RoomCallback).
 */
fun getDefaultCategories(): List<CategoryEntity> {
    val categorias = mutableListOf<CategoryEntity>()

    // --- 10 CATEGORÍAS DE GASTO ---

    categorias.add(
        CategoryEntity(
            id = 1,
            name = "Alimentos y Bebidas",
            type = CategoryEntityType.EXPENSE,
            color = COLOR_GASTO_ROJO,
            iconName = "GroceryShopping",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 2,
            name = "Transporte",
            type = CategoryEntityType.EXPENSE,
            color = COLOR_GASTO_AZUL,
            iconName = "Car",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 3,
            name = "Vivienda y Servicios",
            type = CategoryEntityType.EXPENSE,
            color = COLOR_GASTO_NARANJA,
            iconName = "Home",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 4,
            name = "Entretenimiento",
            type = CategoryEntityType.EXPENSE,
            color = 0xFF673AB7L, // Púrpura oscuro
            iconName = "MovieTicket",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 5,
            name = "Salud y Cuidado Personal",
            type = CategoryEntityType.EXPENSE,
            color = 0xFF00BCD4L, // Cyan
            iconName = "Health",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 6,
            name = "Educación",
            type = CategoryEntityType.EXPENSE,
            color = 0xFFFFC107L, // Ámbar
            iconName = "Book",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 7,
            name = "Ropa y Accesorios",
            type = CategoryEntityType.EXPENSE,
            color = 0xFFF44336L, // Rojo intenso
            iconName = "ShoppingBag",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 8,
            name = "Deudas y Préstamos",
            type = CategoryEntityType.EXPENSE,
            color = 0xFF795548L, // Marrón
            iconName = "Loan",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 9,
            name = "Mascotas",
            type = CategoryEntityType.EXPENSE,
            color = 0xFF8BC34AL, // Verde claro
            iconName = "DogPaw",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 10,
            name = "Otros Gastos",
            type = CategoryEntityType.EXPENSE,
            color = 0xFF9E9E9EL, // Gris
            iconName = "OtherExpense",
            isActive = true
        )
    )


    // --- 5 CATEGORÍAS DE INGRESO ---

    categorias.add(
        CategoryEntity(
            id = 11,
            name = "Salario",
            type = CategoryEntityType.INCOME,
            color = COLOR_INGRESO_VERDE,
            iconName = "MoneyBag",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 12,
            name = "Inversiones",
            type = CategoryEntityType.INCOME,
            color = 0xFF009688L, // Teal
            iconName = "Investment",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 13,
            name = "Regalos y Donaciones",
            type = CategoryEntityType.INCOME,
            color = 0xFFFFEB3BL, // Amarillo
            iconName = "Gift",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 14,
            name = "Ventas (Ingreso Extra)",
            type = CategoryEntityType.INCOME,
            color = 0xFFCDDC39L, // Lima
            iconName = "Sales",
            isActive = true
        )
    )
    categorias.add(
        CategoryEntity(
            id = 15,
            name = "Otros Ingresos",
            type = CategoryEntityType.INCOME,
            color = 0xFF607D8BL, // Azul Gris
            iconName = "OtherIncome",
            isActive = true
        )
    )

    return categorias.toList()
}