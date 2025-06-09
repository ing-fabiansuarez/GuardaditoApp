package software.mys.guardaditoapp.ui.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import software.mys.guardaditoapp.ui.util.AppColors

class AccountFormViewModel : ViewModel() {
    private val _uiState = mutableStateOf(AccountFormState())
    val uiState: State<AccountFormState> = _uiState

    fun setName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun setType(type: String) {
        _uiState.value = _uiState.value.copy(type = type)
    }

    fun setIcon(icon: ImageVector) {
        _uiState.value = _uiState.value.copy(selectedIcon = icon)
    }

    fun setColor(color: Long) {
        _uiState.value = _uiState.value.copy(selectedColor = color)
    }

    fun setBalance(balance: String) {
        _uiState.value = _uiState.value.copy(balance = balance)
    }
}

data class AccountFormState(
    val name: String = "",
    val type: String = "",
    val balance: String = "0.0",
    val selectedIcon: ImageVector = Icons.Default.AccountBalanceWallet,
    val selectedColor: Long = AppColors.colors.first(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val isValid: Boolean
        get() = name.isNotBlank() && type.isNotBlank() && balance.isNotBlank()
}