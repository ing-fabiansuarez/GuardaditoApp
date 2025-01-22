package software.mys.guardaditoapp.data

import software.mys.guardaditoapp.model.Account
import software.mys.guardaditoapp.model.Expense
import software.mys.guardaditoapp.model.Income

val accountsFake = listOf(
    Account(
        id = "1",
        name = "Cuenta Principal",
        balance = 1500.75,
        type = "Ahorros",
        incomes = listOf(
            Income(amount = 500.0, date = "2024-12-01", category = "Salario"),
            Income(amount = 200.0, date = "2024-12-10", category = "Reembolso")
        ),
        expenses = listOf(
            Expense(amount = 100.0, date = "2024-12-05", category = "Comida"),
            Expense(amount = 50.0, date = "2024-12-12", category = "Transporte")
        )
    ),
    Account(
        id = "2",
        name = "Fondo de Emergencia",
        balance = 10000.0,
        type = "Ahorros",
        incomes = listOf(
            Income(amount = 5000.0, date = "2024-11-01", category = "Inversión")
        ),
        expenses = listOf(
            Expense(amount = 2000.0, date = "2024-12-01", category = "Reparación Hogar")
        )
    ),
    Account(
        id = "3",
        name = "Cuenta de Inversiones",
        balance = 25000.0,
        type = "Corriente",
        incomes = listOf(
            Income(amount = 10000.0, date = "2024-11-15", category = "Intereses"),
            Income(amount = 5000.0, date = "2024-12-01", category = "Dividendos")
        ),
        expenses = listOf(
            Expense(amount = 1000.0, date = "2024-12-05", category = "Comisiones")
        )
    ),
    Account(
        id = "4",
        name = "Gastos Hogar",
        balance = 750.0,
        type = "Corriente",
        incomes = listOf(
            Income(amount = 1000.0, date = "2024-12-01", category = "Reembolso")
        ),
        expenses = listOf(
            Expense(amount = 500.0, date = "2024-12-03", category = "Servicios Públicos"),
            Expense(amount = 200.0, date = "2024-12-05", category = "Compras")
        )
    ),
    Account(
        id = "5",
        name = "Viajes",
        balance = 3200.0,
        type = "Ahorros",
        incomes = listOf(
            Income(amount = 2000.0, date = "2024-10-15", category = "Ahorro Programado")
        ),
        expenses = listOf(
            Expense(amount = 1000.0, date = "2024-11-20", category = "Hoteles"),
            Expense(amount = 500.0, date = "2024-11-22", category = "Transporte")
        )
    ),
    Account(
        id = "6",
        name = "Educación",
        balance = 4000.0,
        type = "Ahorros",
        incomes = listOf(
            Income(amount = 1500.0, date = "2024-10-01", category = "Subsidio")
        ),
        expenses = listOf(
            Expense(amount = 2000.0, date = "2024-12-10", category = "Matrícula")
        )
    ),
    Account(
        id = "7",
        name = "Cuenta Personal",
        balance = 500.0,
        type = "Corriente",
        incomes = listOf(
            Income(amount = 500.0, date = "2024-12-01", category = "Salario")
        ),
        expenses = listOf(
            Expense(amount = 300.0, date = "2024-12-02", category = "Compras"),
            Expense(amount = 50.0, date = "2024-12-05", category = "Comida")
        )
    ),
    Account(
        id = "8",
        name = "Vehículo",
        balance = 8000.0,
        type = "Ahorros",
        incomes = listOf(
            Income(amount = 5000.0, date = "2024-09-15", category = "Venta de Activos")
        ),
        expenses = listOf(
            Expense(amount = 2000.0, date = "2024-12-01", category = "Mantenimiento")
        )
    ),
    Account(
        id = "9",
        name = "Entretenimiento",
        balance = 1200.0,
        type = "Corriente",
        incomes = listOf(
            Income(amount = 1000.0, date = "2024-11-01", category = "Bonos")
        ),
        expenses = listOf(
            Expense(amount = 300.0, date = "2024-11-15", category = "Cine"),
            Expense(amount = 200.0, date = "2024-11-20", category = "Conciertos")
        )
    ),
    Account(
        id = "10",
        name = "Salud",
        balance = 2000.0,
        type = "Ahorros",
        incomes = listOf(
            Income(amount = 2000.0, date = "2024-12-01", category = "Ahorro Programado")
        ),
        expenses = listOf(
            Expense(amount = 1000.0, date = "2024-12-05", category = "Medicamentos"),
            Expense(amount = 500.0, date = "2024-12-10", category = "Consultas Médicas")
        )
    )
)
