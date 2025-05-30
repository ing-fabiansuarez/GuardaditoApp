package software.mys.guardaditoapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.local.entities.AccountEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType
import software.mys.guardaditoapp.data.local.entities.TransactionEntity
import software.mys.guardaditoapp.data.local.entities.toCategoryUi
import software.mys.guardaditoapp.data.local.entities.toUi
import software.mys.guardaditoapp.data.repositories.AccountRepository
import software.mys.guardaditoapp.data.repositories.CategoryRepository
import software.mys.guardaditoapp.data.repositories.TransactionRepository
import software.mys.guardaditoapp.ui.models.AccountUi
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.models.TransactionUi
import software.mys.guardaditoapp.ui.models.toEntityModel

class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    var categories = mutableListOf<CategoryUi>()
    var accounts = listOf<AccountUi>()

    val db = AppDatabase.getInstance(application.applicationContext)
    val accountRepository = AccountRepository(db.accountDao())
    val categoryRepository = CategoryRepository(db.categoryDao())
    val transactionRepository = TransactionRepository(db.transactionDao(), db.accountDao())

    init {
        accounts = accountRepository.getAllAccounts().map {
            it.toUi()
        }


        categoryRepository.getAllCategories().onEach { listCate ->
            listCate.forEach {
                categories.add(it.toCategoryUi())
            }
        }.launchIn(viewModelScope)

        if (accounts.isEmpty()) {
            accountRepository.insert(
                AccountEntity(
                    name = "Efectivo",
                    type = "Disponible",
                    balance = 500_000.0,
                    icon = "attach_money",  // o el nombre de tu ícono local
                    color = 0xFFE57373 // Rojo claro
                )
            )
            accountRepository.insert(
                AccountEntity(
                    name = "Cuenta Bancaria",
                    type = "Ahorros",
                    balance = 2_000_000.0,
                    icon = "account_balance", // o tu nombre de ícono correspondiente
                    color = 0xFF64B5F6 // Azul claro
                )
            )
            accounts = accountRepository.getAllAccounts().map {
                it.toUi()
            }


        }

        if (categories.isEmpty()) {
            categoryRepository.insert(
                CategoryEntity(
                    name = "Alimentación",
                    type = CategoryEntityType.EXPENSE,
                    color = 0xFFFF7043, // Naranja
                    iconName = "restaurant"
                )
            )
            categoryRepository.insert(
                CategoryEntity(
                    name = "Salario",
                    type = CategoryEntityType.INCOME,
                    color = 0xFF66BB6A, // Verde
                    iconName = "attach_money"
                )
            )
            categoryRepository.insert(
                CategoryEntity(
                    name = "Transporte",
                    type = CategoryEntityType.EXPENSE,
                    color = 0xFF42A5F5, // Azul
                    iconName = "directions_car"
                )
            )
            categoryRepository.insert(
                CategoryEntity(
                    name = "Freelance",
                    type = CategoryEntityType.INCOME,
                    color = 0xFFAB47BC, // Morado
                    iconName = "work"
                )
            )
            categoryRepository.insert(
                CategoryEntity(
                    name = "Entretenimiento",
                    type = CategoryEntityType.EXPENSE,
                    color = 0xFFFFCA28, // Amarillo
                    iconName = "movie"
                )
            )
            categoryRepository.getAllCategories().onEach { listCate ->
                listCate.forEach {
                    categories.add(it.toCategoryUi())
                }
            }.launchIn(viewModelScope)
        }


        Log.i("mensajes", categories.toString())
        Log.i("mensajes", accounts.toString())
    }


    fun addNewTransaction(transaction: TransactionUi) {
        transactionRepository.insertTransaction(transaction.toEntityModel())
    }


}

data class TransactionUiState(
    val listCategories: List<CategoryUi> = emptyList(),
)