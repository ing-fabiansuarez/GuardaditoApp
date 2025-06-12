package software.mys.guardaditoapp.data.repositories

import kotlinx.coroutines.flow.Flow
import software.mys.guardaditoapp.data.local.daos.CategoryDao
import software.mys.guardaditoapp.data.local.entities.CategoryEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType

class CategoryRepository(private val categoryDao: CategoryDao) {

    fun save(category: CategoryEntity) {
        if (category.id == 0L) {
            categoryDao.insert(category)
        } else {
            categoryDao.update(category)
        }
    }

    fun getCatgoriesByType(type: CategoryEntityType): List<CategoryEntity> {
        return categoryDao.getCategoriesByType(type)
    }

    fun getAllCategories(): Flow<List<CategoryEntity>> {
        return categoryDao.getAll()
    }

    fun deleteCategory(category: CategoryEntity) {
        categoryDao.delete(category)
    }

    fun getCategoriesByTypeFlow(type: CategoryEntityType): Flow<List<CategoryEntity>> {
        return categoryDao.getCategoriesByTypeFlow(type)
    }

}