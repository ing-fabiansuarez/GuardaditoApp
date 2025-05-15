package software.mys.guardaditoapp.ui.screen.components.floating_actions_button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryFAB(onClickListener: () -> Unit = {}) {
    FloatingActionButton(
        onClick = onClickListener,
    ) {
        Icon(Icons.Filled.Add, "Agregar Categoria.")
    }
}