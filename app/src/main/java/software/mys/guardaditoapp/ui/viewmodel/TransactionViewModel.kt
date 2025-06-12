package software.mys.guardaditoapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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
    }

    fun addNewTransaction(transaction: TransactionUi) {
        transactionRepository.insertTransaction(transaction.toEntityModel())
    }

}