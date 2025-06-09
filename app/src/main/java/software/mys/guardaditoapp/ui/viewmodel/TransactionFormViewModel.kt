package software.mys.guardaditoapp.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.local.entities.TransactionEntity
import software.mys.guardaditoapp.data.local.entities.TransactionTypeEntity
import software.mys.guardaditoapp.data.local.entities.toUi
import software.mys.guardaditoapp.data.repositories.AccountRepository
import software.mys.guardaditoapp.data.repositories.CategoryRepository
import software.mys.guardaditoapp.ui.models.AccountUi
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.models.TransactionTypeUi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class TransactionFormUiState(
    val amount: String = "",
    val account: AccountUi = AccountUi(),
    val category: CategoryUi = CategoryUi(),
    val detail: String = "",
    val date: String = "",
    val formValid: Boolean = false,
    val amountError: String? = null,
    val accountError: String? = null,
    val categoryError: String? = null,
    val detailError: String? = null,
    val dateError: String? = null,
    var categories: List<CategoryUi> = mutableListOf<CategoryUi>(),
    var accounts: List<AccountUi> = listOf<AccountUi>()
)

class TransactionFormViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(TransactionFormUiState())
    val uiState: StateFlow<TransactionFormUiState> get() = _uiState

    val db = AppDatabase.getInstance(application.applicationContext)
    val accountRepository = AccountRepository(db.accountDao())
    val categoryRepository = CategoryRepository(db.categoryDao())

    init {
        loadInitialData()
    }

    fun loadInitialData() {
        _uiState.update {
            it.copy(
                accounts = accountRepository.getAllAccounts().map {
                    it.toUi()
                }
            )
        }
    }

    fun updateField(field: String, value: Any?) {
        _uiState.update {
            when (field) {
                "amount" -> it.copy(amount = value as String, amountError = validateAmount(value))
                "account" -> it.copy(
                    account = value as AccountUi, accountError = validateAccount(
                        value
                    )
                )

                "category" -> it.copy(
                    category = value as CategoryUi, categoryError = validateCategory(
                        value
                    )
                )

                "detail" -> it.copy(
                    detail = value as String,
                    detailError = validateDetail(value)
                )

                "date" -> it.copy(date = value as String, dateError = validateDate(value))
                else -> it
            }
        }
        validateForm()
    }

    private fun validateAmount(amount: String): String? {

        return when {
            amount.isBlank() -> "Amount cannot be empty"
            amount.toDoubleOrNull() == null -> "Amount must be a number"
            amount.toDoubleOrNull()!! <= 0 -> "Amount must be greater than 0"
            else -> null
        }
    }

    private fun validateAccount(account: AccountUi): String? {
        return if (account == AccountUi()) "Account must be selected" else null
    }

    private fun validateCategory(category: CategoryUi): String? {
        return if (category == CategoryUi()) "Category must be selected" else null
    }

    private fun validateDetail(detail: String): String? {
        return if (detail.isBlank()) "Detail cannot be empty" else null
    }

    private fun validateDate(date: String): String? {
        return if (!isValidDate(date)) "Invalid date format" else null
    }

    private fun isValidDate(date: String): Boolean {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            sdf.isLenient = false
            sdf.parse(date)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun validateForm() {
        _uiState.update {
            it.copy(
                amountError = validateAmount(it.amount),
                accountError = validateAccount(it.account),
                categoryError = validateCategory(it.category),
                detailError = validateDetail(it.detail),
                dateError = validateDate(it.date),
                formValid = it.amountError == null && it.accountError == null &&
                        it.categoryError == null && it.detailError == null &&
                        it.dateError == null
            )
        }
    }


    fun onSave(onSaveSuccesful: (TransactionFormUiState) -> Unit): Unit {
        validateForm()
        if (uiState.value.formValid) {
            onSaveSuccesful(uiState.value)
        }
    }
}
