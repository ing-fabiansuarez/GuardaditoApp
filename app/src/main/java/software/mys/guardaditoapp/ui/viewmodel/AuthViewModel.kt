package software.mys.guardaditoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class AuthViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _isLoggedIn = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)
    val email: StateFlow<String> = _email
    val password: StateFlow<String> = _password
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        val firebaseAuth = Firebase.auth
        if (firebaseAuth.currentUser != null) {
            _isLoggedIn.value = true
        }
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun login(onLoginSuccess: ()->Unit) {
        if (_email.value.isBlank() || _password.value.isBlank()) {
            _errorMessage.value = "Email and Password cannot be empty."
            return
        }

        val firebaseAuth = Firebase.auth

        firebaseAuth.signInWithEmailAndPassword(_email.value, _password.value)
            .addOnCompleteListener() {
                if (it.isSuccessful) {
                    _isLoggedIn.value = true
                    _errorMessage.value = null
                    onLoginSuccess()
                } else {
                    _errorMessage.value = "Invalid email or password."
                }
            }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}