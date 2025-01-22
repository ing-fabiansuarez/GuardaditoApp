package software.mys.guardaditoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import software.mys.guardaditoapp.data.repository.AccountRepository
import software.mys.guardaditoapp.model.User

class TopBarViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(TopBarUiState())
    val uiState: StateFlow<TopBarUiState> = _uiState.asStateFlow()

    init {
        loadCurrentUser()
    }

    private fun loadCurrentUser() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            _uiState.value = TopBarUiState(
                userSesion = User(
                    name = currentUser.displayName ?: "",
                    email = currentUser.email ?: ""
                )
            )
        }
    }

    fun logout(onLogoutComplete: () -> Unit) {
        val auth = FirebaseAuth.getInstance()
        auth.signOut()

        onLogoutComplete()
    }





}

data class TopBarUiState(
    val userSesion: User? = null
)

