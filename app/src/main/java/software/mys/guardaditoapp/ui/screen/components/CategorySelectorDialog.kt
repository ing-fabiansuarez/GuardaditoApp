package software.mys.guardaditoapp.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import software.mys.guardaditoapp.ui.models.CategoryUi

@Composable
fun CategorySelectorDialog(
    category: CategoryUi,
    listCategories: List<CategoryUi>,
    supportingText: @Composable () -> Unit,
    isError: Boolean,
    onItemSelected: (CategoryUi) -> Unit,
) {
    var showCategorySelector by remember { mutableStateOf(false) }
    val availabelCategories = remember {
        listCategories
    }

    OutlinedTextField(
        value = category.name,
        onValueChange = { /* No permitir edición directa */ },
        label = { Text("Categoría") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Category,
                contentDescription = "Categoría"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showCategorySelector = true },
        shape = RoundedCornerShape(12.dp),
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { showCategorySelector = true }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Seleccionar categoría"
                )
            }
        },
        supportingText = supportingText,
        isError = isError,
    )

    // Diálogo de selección de categorías
    if (showCategorySelector) {
        AlertDialog(
            onDismissRequest = { showCategorySelector = false },
            title = { Text("Seleccionar Categoría") },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    availabelCategories.forEach { opcionCategoria ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    onItemSelected(opcionCategoria)
                                    showCategorySelector = false
                                },
                            color = if (category == opcionCategoria)
                                MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surface
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Category,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = opcionCategoria.name,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showCategorySelector = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
}