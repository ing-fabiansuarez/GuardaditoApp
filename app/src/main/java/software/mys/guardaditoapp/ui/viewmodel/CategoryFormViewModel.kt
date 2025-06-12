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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import software.mys.guardaditoapp.ui.util.AppColors
import software.mys.guardaditoapp.ui.util.AppIcons

// ui/viewmodel/CategoryFormViewModel.kt
class CategoryFormViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(CategoryFormState())
    val uiState = _uiState.asStateFlow()

    init {
        Log.i("fabian", "CategoryFormViewModel")
    }

    fun setName(name: String) {
        _uiState.value =
            _uiState.value.copy(categoryUi = _uiState.value.categoryUi.copy(name = name))
    }

    fun setIcon(icon: ImageVector) {
        _uiState.value =
            _uiState.value.copy(categoryUi = _uiState.value.categoryUi.copy(icon = icon))
    }

    fun setType(type: CategoryUiType) {
        _uiState.value =
            _uiState.value.copy(categoryUi = _uiState.value.categoryUi.copy(type = type))
    }

    fun setColor(color: Long) {
        _uiState.value =
            _uiState.value.copy(categoryUi = _uiState.value.categoryUi.copy(color = color))
    }

    fun loadCategory(category: CategoryUi) {
        _uiState.value = _uiState.value.copy(categoryUi = category)
    }
}

data class CategoryFormState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val categoryUi: CategoryUi = CategoryUi()
) {
    val isValid: Boolean
        get() = categoryUi.name.isNotBlank() && error == null
}