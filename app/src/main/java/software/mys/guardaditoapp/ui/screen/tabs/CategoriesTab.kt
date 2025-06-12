package software.mys.guardaditoapp.ui.screen.tabs

import android.app.Application
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.models.CategoryUiType
import software.mys.guardaditoapp.ui.screen.components.EmptyCategoriesMessage


@Composable
fun CategoriesTab(
    listCategories: List<CategoryUi> = listOf(),
    onDeleteCategory: (CategoryUi) -> Unit = {},
    onClickItem: (CategoryUi) -> Unit = {}
) {
    TabsExpenseAndIncome(
        modifier = Modifier,
        incomeCategories = listCategories.filter { it.type == CategoryUiType.INCOME },
        expenseCategories = listCategories.filter { it.type == CategoryUiType.EXPENSE },
        onClickDeleteCategory = onDeleteCategory,
        onClickItem = onClickItem
    )
}

@Composable
fun TabsExpenseAndIncome(
    modifier: Modifier,
    incomeCategories: List<CategoryUi> = listOf(),
    expenseCategories: List<CategoryUi> = listOf(),
    onClickDeleteCategory: (CategoryUi) -> Unit = {},
    onClickItem: (CategoryUi) -> Unit = {}
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
                                onDelete = { category -> onClickDeleteCategory(category) },
                                onClickItem = onClickItem
                            )
                        }
                    }

                }

                CategoryUiType.EXPENSE -> {
                    if (expenseCategories.isEmpty()) {
                        EmptyCategoriesMessage(type = CategoryUiType.EXPENSE)
                    } else {
                        expenseCategories.forEach { category ->
                            CategoryItem(
                                category,
                                onDelete = { category -> onClickDeleteCategory(category) },
                                onClickItem = onClickItem
                            )
                        }
                    }

                }
            }
        }

    }
}

@Composable
fun CategoryItem(
    category: CategoryUi,
    onDelete: (CategoryUi) -> Unit = {},
    onClickItem: (CategoryUi) -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClickItem(category)
            },
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




