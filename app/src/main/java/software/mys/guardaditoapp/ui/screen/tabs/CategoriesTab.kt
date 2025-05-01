package software.mys.guardaditoapp.ui.screen.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.models.CategoryUiType
import software.mys.guardaditoapp.ui.viewmodel.CategoryViewModel
import software.mys.guardaditoapp.ui.screen.components.EmptyCategoriesMessage


@Composable
fun CategoriesTab(viewmodel: CategoryViewModel) {


    val uiState by viewmodel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    TabsExpenseAndIncome(
        modifier = Modifier,
        incomeCategories = uiState.listCategories.filter { it.type == CategoryUiType.INCOME },
        expenseCategories = uiState.listCategories.filter { it.type == CategoryUiType.EXPENSE },
        onClickDeleteCategory = { category ->
            viewmodel.deleteCategory(category)
        }
    )
    // Mostrar el diálogo si showDialog es true
    if (showDialog) {
        FullScreenDialog(
            onDismiss = { showDialog = false } // Cerrar el diálogo
        )
    }
}

@Composable
fun TabsExpenseAndIncome(
    modifier: Modifier,
    incomeCategories: List<CategoryUi> = listOf(),
    expenseCategories: List<CategoryUi> = listOf(),
    onClickDeleteCategory: (CategoryUi) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        var selectedTabIndex by remember { mutableStateOf(0) }

        // Usamos el enum para definir las pestañas
        val tabs = CategoryUiType.entries.toTypedArray()

        Column(modifier = Modifier.fillMaxSize()) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, categoryType ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = categoryType.getTabName()) }
                    )
                }
            }

            when (tabs[selectedTabIndex]) {
                CategoryUiType.INCOME -> {
                    if (incomeCategories.isEmpty()) {
                        EmptyCategoriesMessage(type = CategoryUiType.INCOME)
                    } else {
                        incomeCategories.forEach { category ->
                            CategoryItem(
                                category,
                                onDelete = { category -> onClickDeleteCategory(category) })
                        }
                    }

                }

                CategoryUiType.EXPENSE -> {
                    if (expenseCategories.isEmpty()) {
                        EmptyCategoriesMessage(type = CategoryUiType.EXPENSE)
                    } else {
                        expenseCategories.forEach { category ->
                            CategoryItem(category)
                        }
                    }

                }
            }
        }

    }
}

@Composable
fun FullScreenDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false) // Diálogo de pantalla completa
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Título del diálogo
                Text(
                    text = "Nueva Categoría",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Campo de texto para el nombre de la categoría
                var categoryName by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = categoryName,
                    onValueChange = { categoryName = it },
                    label = { Text("Nombre de la categoría") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                // Botón para guardar la categoría
                Button(
                    onClick = {
                        // Aquí puedes guardar la categoría
                        onDismiss() // Cerrar el diálogo después de guardar
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar")
                }

                // Botón para cancelar
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}




@Composable
fun CategoryItem(category: CategoryUi, onDelete: (CategoryUi) -> Unit = {}) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { }
                    .background(color = Color(category.color)) // Asigna el color aquí
                ,
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = category.icon, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = category.name, fontSize = 16.sp, fontWeight = FontWeight.Medium)

        }
        // Ícono de eliminar
        IconButton(onClick = { onDelete(category) }) {
            Icon(
                imageVector = Icons.Default.Delete, // Cambia esto por el ícono que desees usar
                contentDescription = "Eliminar categoría",
                tint = Color.Red
            )
        }
    }
    HorizontalDivider()
}




