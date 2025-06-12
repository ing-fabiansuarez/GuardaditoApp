package software.mys.guardaditoapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.local.entities.toCategoryUi
import software.mys.guardaditoapp.data.repositories.CategoryRepository
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.models.toEntity

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val db = AppDatabase.getInstance(application.applicationContext)
    val categoryRepository = CategoryRepository(db.categoryDao())

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()


    fun addNewCategory(category: CategoryUi) {
        categoryRepository.save(category.toEntity())
    }

    fun loadAllCategories() {
        // Observamos el Flow desde el repositorio para actualizar el estado
        categoryRepository.getAllCategories()
            .onEach { categories ->
                _uiState.value = _uiState.value.copy(
                    listCategories = categories.map { it.toCategoryUi() }
                )
            }
            .launchIn(viewModelScope)
    }

    fun deleteCategory(category: CategoryUi) {
        categoryRepository.deleteCategory(category.toEntity())
    }


}

data class MainUiState(
    val listCategories: List<CategoryUi> = emptyList(),
    val categoryFormUi: CategoryUi = CategoryUi()
)