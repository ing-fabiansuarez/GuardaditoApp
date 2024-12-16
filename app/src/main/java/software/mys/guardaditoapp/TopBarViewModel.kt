package software.mys.guardaditoapp

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class TopBarUiState(
    val userSesion: User? = null
)

class TopBarViewModel : ViewModel() {
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