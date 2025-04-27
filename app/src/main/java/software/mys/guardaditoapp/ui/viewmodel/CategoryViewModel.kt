package software.mys.guardaditoapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType
import software.mys.guardaditoapp.data.local.entities.toCategoryUi
import software.mys.guardaditoapp.data.repositories.CategoryRepository
import software.mys.guardaditoapp.ui.models.CategoryUi


class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState = _uiState.asStateFlow()
    val db = AppDatabase.getInstance(application.applicationContext)
    val categoryRepository = CategoryRepository(db.categoryDao())

    init {
        _uiState.value = _uiState.value.copy(
            incomeCategories = categoryRepository.getCatgoriesByType(CategoryEntityType.INCOME).map { it.toCategoryUi() },
            expenseCategories = categoryRepository.getCatgoriesByType(CategoryEntityType.EXPENSE).map { it.toCategoryUi() }
        )
    }

}

data class CategoryUiState(
    val incomeCategories: List<CategoryUi> = emptyList(),
    val expenseCategories: List<CategoryUi> = emptyList()
)



