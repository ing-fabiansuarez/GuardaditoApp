package software.mys.guardaditoapp

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import software.mys.guardaditoapp.ui.screen.CategoryFormScreen
import software.mys.guardaditoapp.ui.screen.MainScreen

@Composable
fun NavigationApp() {
    val context = LocalContext.current
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Main.route) {
        composable(Routes.Main.route) {
            MainScreen(onClickAddCategory = {
                navController.navigate(Routes.CategoryForm.route)
            })
        }
        composable(Routes.CategoryForm.route) {
            CategoryFormScreen(onCloseClick = {
                navController.popBackStack()
            }, onSaveComplete = {
                Toast.makeText(context, "Categoría guardada", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            })
        }
    }

}

