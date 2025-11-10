package software.mys.guardaditoapp.ui.screen.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Assessment
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import software.mys.guardaditoapp.Routes


data class BottomNavigationItem(
    val route: String,
    val icon: ImageVector,
    val unSelectedIcon: ImageVector,
    val title: String
)

val items = listOf(
    BottomNavigationItem(
        route = Routes.HomeTab.route,
        icon = Icons.Default.Home,
        unSelectedIcon = Icons.Outlined.Home,
        title = Routes.HomeTab.route
    ),
    BottomNavigationItem(
        route = Routes.CategoriesTab.route,
        icon = Icons.Default.Category,
        unSelectedIcon = Icons.Outlined.Category,
        title = Routes.CategoriesTab.route
    ),
    BottomNavigationItem(
        route = Routes.ReportsTab.route,
        icon = Icons.Default.Assessment,
        unSelectedIcon = Icons.Outlined.Assessment,
        title = Routes.ReportsTab.route
    )
)

@Composable
fun NavigationBottomAppBar(navController: NavHostController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (selectedItem == index) item.icon else item.unSelectedIcon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                selected = selectedItem == index,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    selectedItem = index
                }
            )
        }
    }
}


