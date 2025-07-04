package software.mys.guardaditoapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.repositories.AccountRepository
import software.mys.guardaditoapp.ui.models.AccountUi
import software.mys.guardaditoapp.data.local.entities.toUi
import software.mys.guardaditoapp.ui.models.toEntity

data class AccountUiState(
    val accounts: List<AccountUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class AccountViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(AccountUiState())
    val uiState = _uiState.asStateFlow()

    private val db = AppDatabase.getInstance(application.applicationContext)
    private val accountRepository = AccountRepository(db.accountDao())

    init {
        loadAccounts()
        Log.i("fabian","AccountViewModel")
    }

    private fun loadAccounts() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        val accounts = accountRepository.getAllAccounts().map { it.toUi() }
        _uiState.value = _uiState.value.copy(
            accounts = accounts,
            isLoading = false
        )
    }

    fun addAccount(account: AccountUi) {
        accountRepository.save(account.toEntity())
        loadAccounts()
    }

    fun deleteAccount(account: AccountUi) {
        accountRepository.delete(account.toEntity())
        loadAccounts()
    }
}

class AccountViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}