package software.mys.guardaditoapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType
import software.mys.guardaditoapp.data.local.entities.toCategoryUi
import software.mys.guardaditoapp.data.repositories.CategoryRepository
import software.mys.guardaditoapp.ui.models.CategoryUi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn


class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState = _uiState.asStateFlow()

    val db = AppDatabase.getInstance(application.applicationContext)
    val categoryRepository = CategoryRepository(db.categoryDao())

    init {
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

    fun addNewCategory(category: CategoryUi) {
        categoryRepository.insert(category.toEntity())
    }

}

data class CategoryUiState(
    val listCategories: List<CategoryUi> = emptyList(),
)

class CategoryViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


