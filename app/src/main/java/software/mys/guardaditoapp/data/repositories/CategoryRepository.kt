package software.mys.guardaditoapp.data.repositories

import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.local.daos.CategoryDao
import software.mys.guardaditoapp.data.local.entities.CategoryEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType

class CategoryRepository(private val categoryDao: CategoryDao) {

    fun getAllCategories(): List<CategoryEntity> {
        return categoryDao.getAll()
    }

    fun getCatgoriesByType(type: CategoryEntityType): List<CategoryEntity> {
        return categoryDao.getCategoriesByType(type)
    }

}