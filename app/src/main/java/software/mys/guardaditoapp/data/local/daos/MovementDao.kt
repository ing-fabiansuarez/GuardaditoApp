package software.mys.guardaditoapp.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import software.mys.guardaditoapp.data.local.entities.MovementEntity
import software.mys.guardaditoapp.data.local.entities.MovementType
import java.util.Date

@Dao
interface MovementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovement(movement: MovementEntity): Long
}