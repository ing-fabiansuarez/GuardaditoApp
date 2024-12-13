package software.mys.guardaditoapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _isLoggedIn = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)
    val email: StateFlow<String> = _email
    val password: StateFlow<String> = _password
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn
    val errorMessage: StateFlow<String?> = _errorMessage

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun login() {
        if (_email.value.isBlank() || _password.value.isBlank()) {
            _errorMessage.value = "Email and Password cannot be empty."
            return
        }

        // Simulate login success or failure
        if (_email.value == "test@example.com" && _password.value == "password") {
            _isLoggedIn.value = true
            _errorMessage.value = null
        } else {
            _errorMessage.value = "Invalid email or password."
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}