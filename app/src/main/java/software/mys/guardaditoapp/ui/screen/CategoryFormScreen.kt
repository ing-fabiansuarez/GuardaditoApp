package software.mys.guardaditoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import software.mys.guardaditoapp.ui.viewmodel.CategoryFormViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.util.AppIcons
import software.mys.guardaditoapp.ui.models.CategoryUiType
import software.mys.guardaditoapp.ui.util.AppColors


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CategoryFormScreen(
    viewModel: CategoryFormViewModel = viewModel(),
    onCloseClick: () -> Unit = {},
    onSaveComplete: (CategoryUi) -> Unit = {}
) {

    val uiState by viewModel.uiState

    // Muestra diálogo de error si existe
    if (uiState.error != null) {
        AlertDialog(
            onDismissRequest = viewModel::dismissError,
            title = { Text("Error") },
            text = { Text(uiState.error!!) },
            confirmButton = {
                Button(onClick = viewModel::dismissError) {
                    Text("OK")
                }
            }
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Categoría") },
                navigationIcon = {
                    IconButton(onClick = onCloseClick) {
                        Icon(Icons.Default.Close, contentDescription = "Cancelar")
                    }
                })
        }
    ) { paddingValues ->
        Card(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                OutlinedTextField(
                    value = uiState.name,
                    onValueChange = viewModel::setName,
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                InputCategoryType(
                    modifier = Modifier.fillMaxWidth(),
                    selectedType = uiState.selectedType,
                    onTypeSelected = { type: CategoryUiType -> viewModel.setType(type) }
                )
                InputColor(onColorSelected = { colorValue ->
                    viewModel.setColor(colorValue)
                }, selectedColor = uiState.selectedColor)

                InputIcon(onIconSelected = { icon: ImageVector ->
                    viewModel.setIcon(icon)
                }, selectedIcon = uiState.selectedIcon)


                // Botón Guardar
                Button(
                    onClick = {
                        onSaveComplete(
                            CategoryUi(
                                name = uiState.name,
                                type = uiState.selectedType,
                                color = uiState.selectedColor,
                                icon = uiState.selectedIcon
                            )
                        )
                        onCloseClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    enabled = uiState.isValid && !uiState.isLoading
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InputIcon(onIconSelected: (ImageVector) -> Unit, selectedIcon: ImageVector) {
    Text("Icono", style = MaterialTheme.typography.bodyMedium)
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        AppIcons.getAllIcons().map { it.second }.forEach { icon ->
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .border(
                        width = if (icon == selectedIcon) 2.dp else 1.dp,
                        color = if (icon == selectedIcon)
                            MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.outline,
                        shape = CircleShape
                    )
                    .clickable { onIconSelected(icon) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InputColor(onColorSelected: (Long) -> Unit = {}, selectedColor: Long) {
    Text("Color", style = MaterialTheme.typography.bodyMedium)
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        AppColors.colors.forEach { colorValue ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(colorValue))
                    .border(
                        width = if (colorValue == selectedColor) 3.dp else 0.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
                    .clickable {
                        onColorSelected(colorValue)
                    }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputCategoryType(
    modifier: Modifier = Modifier,
    selectedType: CategoryUiType,
    onTypeSelected: (CategoryUiType) -> Unit
) {
    Text("Tipo", style = MaterialTheme.typography.bodyMedium)
    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        CategoryUiType.entries.forEachIndexed { index, type ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = CategoryUiType.entries.size
                ),
                onClick = { onTypeSelected(type) },
                selected = selectedType == type,
                label = { Text(type.name) }
            )
        }

    }
}
