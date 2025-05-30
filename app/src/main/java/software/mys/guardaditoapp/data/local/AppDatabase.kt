package software.mys.guardaditoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import software.mys.guardaditoapp.data.local.daos.TransactionDao
import software.mys.guardaditoapp.data.local.daos.AccountDao
import software.mys.guardaditoapp.data.local.daos.CategoryDao
import software.mys.guardaditoapp.data.local.daos.StadisticsDao
import software.mys.guardaditoapp.data.local.entities.AccountEntity
import software.mys.guardaditoapp.data.local.entities.CategoryEntity
import software.mys.guardaditoapp.data.local.entities.TransactionEntity

@Database(
    entities = [
        CategoryEntity::class,
        AccountEntity::class,
        TransactionEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao
    abstract fun stadisticsDao(): StadisticsDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).allowMainThreadQueries().build()
            } else {
                return INSTANCE!!
            }
        }
    }
}
