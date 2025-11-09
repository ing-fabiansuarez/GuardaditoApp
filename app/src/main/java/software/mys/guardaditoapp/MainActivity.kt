package software.mys.guardaditoapp

import android.os.Bundle

import androidx.activity.compose.setContent

import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import software.mys.guardaditoapp.data.local.AppDatabase
import software.mys.guardaditoapp.data.repositories.AccountRepository
import software.mys.guardaditoapp.data.repositories.CategoryRepository
import software.mys.guardaditoapp.ui.theme.GuardaditoAppTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = AppDatabase.getInstance(applicationContext)
        val categoryRepository = CategoryRepository(db.categoryDao())
        val accountRepository = AccountRepository(db.accountDao())
        if (isFirstTime(this)) {
            categoryRepository.insertDefaultCategories()
            accountRepository.insertDefaultAccounts()
        }
        setContent {
            GuardaditoAppTheme {
                AppNavigation()
            }
        }
    }
}





