package software.mys.guardaditoapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class TopBarUiState(
    val userSesion: User = User(name = "Fabian", email = "fsuarez120@unab.edu.co")
)

class TopBarViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TopBarUiState())
    val uiState: StateFlow<TopBarUiState> = _uiState.asStateFlow()

}