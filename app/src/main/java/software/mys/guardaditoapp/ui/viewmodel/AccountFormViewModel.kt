package software.mys.guardaditoapp.ui.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import software.mys.guardaditoapp.ui.models.AccountUi
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.util.AppColors

class AccountFormViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(AccountFormState())
    val uiState = _uiState.asStateFlow()


    fun setName(name: String) {
        _uiState.value = _uiState.value.copy(accountUi = _uiState.value.accountUi.copy(name = name))
    }

    fun setType(type: String) {
        _uiState.value = _uiState.value.copy(accountUi = _uiState.value.accountUi.copy(type = type))
    }

    fun setColor(color: Long) {
        _uiState.value =
            _uiState.value.copy(accountUi = _uiState.value.accountUi.copy(colorHex = color))
    }



    fun setBalanceText(balance: String) {
        _uiState.value = _uiState.value.copy(
            balanceTxt = balance
        )
        _uiState.value = _uiState.value.copy(
            accountUi = _uiState.value.accountUi.copy(
                balance = balance.toDoubleOrNull() ?: 0.0
            )
        )
    }

    fun loadCategory(account: AccountUi) {
        _uiState.value = _uiState.value.copy(accountUi = account)
        _uiState.value = _uiState.value.copy(balanceTxt = account.balance.toString())
    }
}

data class AccountFormState(
    val isLoading: Boolean = false,
    val balanceTxt: String = "",
    val error: String? = null,
    val accountUi: AccountUi = AccountUi()
) {
    val isValid: Boolean
        get() = accountUi.name.isNotBlank() && accountUi.type.isNotBlank() && accountUi.balance >= 0
}