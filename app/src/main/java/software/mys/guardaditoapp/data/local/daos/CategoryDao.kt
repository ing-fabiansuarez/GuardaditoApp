package software.mys.guardaditoapp.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import software.mys.guardaditoapp.data.local.entities.CategoryEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntityType


@Dao
interface CategoryDao {

    @Insert
    fun insert(category: CategoryEntity): Long

    @Update
    fun update(category: CategoryEntity)

    @Query("SELECT * FROM categories ORDER BY id ASC")
    fun getAll(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories ORDER BY id ASC")
    fun getAllList(): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE type = :type ORDER BY name ASC")
    fun getCategoriesByType(type: CategoryEntityType): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE type = :type ORDER BY name ASC")
    fun getCategoriesByTypeFlow(type: CategoryEntityType): Flow<List<CategoryEntity>>

    @Delete
    fun delete(category: CategoryEntity)
}