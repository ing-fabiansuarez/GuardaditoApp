package software.mys.guardaditoapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
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
import org.tensorflow.lite.support.label.Category


data class BottomNavigationItem(
    val route: String,
    val icon: ImageVector,
    val unSelectedIcon: ImageVector,
    val title: String
)

val items = listOf(
    BottomNavigationItem(
        route = Routes.Home.route,
        icon = Icons.Default.Home,
        unSelectedIcon = Icons.Outlined.Home,
        title = Routes.Home.title
    ),
    BottomNavigationItem(
        route = Routes.Categories.route,
        icon = Icons.Default.Category,
        unSelectedIcon = Icons.Outlined.Category,
        title = Routes.Categories.title
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


