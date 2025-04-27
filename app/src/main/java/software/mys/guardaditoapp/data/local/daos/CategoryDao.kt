package software.mys.guardaditoapp.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import software.mys.guardaditoapp.data.local.entities.CategoryEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType


@Dao
interface CategoryDao {

    @Insert
    fun insert(category: CategoryEntity): Long

    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE type = :type ORDER BY name ASC")
    fun getCategoriesByType(type: CategoryEntityType): List<CategoryEntity>

    @Delete
    fun delete(category: CategoryEntity)
}