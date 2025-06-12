package software.mys.guardaditoapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.repositories.CategoryRepository
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.models.CategoryUiType
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.vector.ImageVector
import software.mys.guardaditoapp.ui.util.AppColors
import software.mys.guardaditoapp.ui.util.AppIcons

// ui/viewmodel/CategoryFormViewModel.kt
class CategoryFormViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = mutableStateOf(CategoryFormState())
    val uiState: State<CategoryFormState> = _uiState

    init {
        Log.i("fabian", "CategoryFormViewModel")
    }

    fun setName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun setIcon(icon: ImageVector) {
        _uiState.value = _uiState.value.copy(selectedIcon = icon)
    }

    fun setType(type: CategoryUiType) {
        _uiState.value = _uiState.value.copy(selectedType = type)
    }

    fun setColor(color: Long) {
        _uiState.value = _uiState.value.copy(selectedColor = color)
    }

    fun saveCategory() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                if (_uiState.value.name.isNotBlank()) {
                    val category = CategoryUi(
                        name = _uiState.value.name,
                        type = _uiState.value.selectedType,
                        color = _uiState.value.selectedColor,
                        icon = _uiState.value.selectedIcon
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error al guardar la categoría: ${e.message}"
                )
            }
        }
    }

    fun dismissError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class CategoryFormState(
    val name: String = "",
    val selectedType: CategoryUiType = CategoryUiType.INCOME,
    val selectedColor: Long = AppColors.colors.first(),
    val selectedIcon: ImageVector = AppIcons.getAllIcons().first().second, // Nuevo campo
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val isValid: Boolean
        get() = name.isNotBlank() && error == null
}