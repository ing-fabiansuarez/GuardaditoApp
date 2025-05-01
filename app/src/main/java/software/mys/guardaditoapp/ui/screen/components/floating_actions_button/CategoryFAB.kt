package software.mys.guardaditoapp.ui.screen.components.floating_actions_button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import software.mys.guardaditoapp.ui.screen.CategoryFormScreen
import software.mys.guardaditoapp.ui.viewmodel.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryFAB(onClickListener: () -> Unit = {}) {
    FloatingActionButton(
        onClick = onClickListener,
    ) {
        Icon(Icons.Filled.Add, "Agregar Categoria.")
    }
}