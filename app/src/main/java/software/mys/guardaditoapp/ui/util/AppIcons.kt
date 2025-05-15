package software.mys.guardaditoapp.ui.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Help
import androidx.compose.ui.graphics.vector.ImageVector

object AppIcons {
    private val iconMap: Map<String, ImageVector> = mapOf(
        "Default.Home" to Icons.Default.Home,
        "Default.ShoppingCart" to Icons.Default.ShoppingCart,
        "Default.Restaurant" to Icons.Default.Restaurant,
        "Default.DirectionsCar" to Icons.Default.DirectionsCar,
        "Default.AttachMoney" to Icons.Default.AttachMoney,
        "Default.HealthAndSafety" to Icons.Default.HealthAndSafety,
        "Default.School" to Icons.Default.School,
        "Default.ChildCare" to Icons.Default.ChildCare,
        "Default.Star" to Icons.Default.Star,
        "Default.Work" to Icons.Default.Work,
        "Default.Favorite" to Icons.Default.Favorite,
        "Default.AccountCircle" to Icons.Default.AccountCircle,
        "Default.Settings" to Icons.Default.Settings,
        "Default.Email" to Icons.Default.Email,
        "Default.Phone" to Icons.Default.Phone,
        "Default.CameraAlt" to Icons.Default.CameraAlt,
        "Default.MusicNote" to Icons.Default.MusicNote,
        "Default.Movie" to Icons.Default.Movie,
        "Default.SportsSoccer" to Icons.Default.SportsSoccer,
        "Default.Flight" to Icons.Default.Flight,
        "Default.LocalHospital" to Icons.Default.LocalHospital,
        "Default.ShoppingBasket" to Icons.Default.ShoppingBasket,
        "Default.LocalGroceryStore" to Icons.Default.LocalGroceryStore,
        "Default.DirectionsBus" to Icons.Default.DirectionsBus,
        "Default.Computer" to Icons.Default.Computer,
        "Default.Pets" to Icons.Default.Pets,
        "Default.LocalCafe" to Icons.Default.LocalCafe,
        "Default.FitnessCenter" to Icons.Default.FitnessCenter,
        "Default.Book" to Icons.Default.Book,
        "Default.Business" to Icons.Default.Business,
        "AutoMirrored.Filled.Help" to Icons.AutoMirrored.Filled.Help
    )

    fun getIconByName(name: String): ImageVector {
        return iconMap[name] ?: Icons.AutoMirrored.Filled.Help // Icono por defecto si no encuentra
    }

    fun getNameByIcon(icon: ImageVector): String {
        return iconMap.entries.firstOrNull { it.value == icon }?.key ?: "Default.Help"
    }

    fun getAllIcons(): List<Pair<String, ImageVector>> {
        return iconMap.entries.map { it.toPair() }
    }
}