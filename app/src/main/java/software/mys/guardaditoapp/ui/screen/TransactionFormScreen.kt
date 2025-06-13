package software.mys.guardaditoapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.models.CategoryUiType
import software.mys.guardaditoapp.ui.models.TransactionTypeUi
import software.mys.guardaditoapp.ui.models.TransactionUi
import software.mys.guardaditoapp.ui.screen.components.AccountSelectorDialog
import software.mys.guardaditoapp.ui.screen.components.CategorySelectorDialog
import software.mys.guardaditoapp.ui.screen.components.DateSelectorDialog
import software.mys.guardaditoapp.ui.viewmodel.TransactionFormViewModel
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionFormScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    transactionType: TransactionTypeUi,
) {

    val context = LocalContext.current
    val transactionFormViewModel: TransactionFormViewModel = viewModel()
    val uiState by transactionFormViewModel.uiState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Text(transactionType.name.lowercase())
                },
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    // Campo Monto
                    OutlinedTextField(
                        value = uiState.amount.toString(),
                        onValueChange = { it ->
                            transactionFormViewModel.updateField("amount", it)
                        },
                        label = { Text("Monto") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Savings,
                                contentDescription = "Monto"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        supportingText = {
                            uiState.amountError?.let {
                                Text(it, color = Color.Red, fontSize = 12.sp)
                            }
                        },
                        isError = !uiState.amountError.isNullOrEmpty(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Decimal
                        )
                    )


                    // Campo Account XD
                    AccountSelectorDialog(
                        account = uiState.let { uiState.account },
                        listAccounts = uiState.accounts,
                        supportingText = {
                            uiState.accountError?.let {
                                Text(it, color = Color.Red, fontSize = 12.sp)
                            }
                        },
                        isError = !uiState.accountError.isNullOrEmpty()
                    ) { itemSelected ->
                        transactionFormViewModel.updateField("account", itemSelected)
                    }


                    // Campo Categoría con selector
                    CategorySelectorDialog(
                        category = uiState.let { uiState.category },
                        listCategories = uiState.categories.filter {
                            when (transactionType) {
                                TransactionTypeUi.INCOME -> it.type == CategoryUiType.INCOME
                                TransactionTypeUi.EXPENSE -> it.type == CategoryUiType.EXPENSE
                                TransactionTypeUi.TRANSFER -> false
                            }
                        },
                        supportingText = {
                            uiState.categoryError?.let {
                                Text(it, color = Color.Red, fontSize = 12.sp)
                            }
                        },
                        isError = !uiState.categoryError.isNullOrEmpty()
                    ) {
                        transactionFormViewModel.updateField("category", it)
                    }

                    // Campo Detalle
                    OutlinedTextField(
                        value = uiState.detail.toString(),
                        onValueChange = {
                            transactionFormViewModel.updateField("detail", it)
                        },
                        label = { Text("Detalle") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Description,
                                contentDescription = "Detalle"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        supportingText = {
                            uiState.detailError?.let {
                                Text(it, color = Color.Red, fontSize = 12.sp)
                            }
                        },
                        isError = !uiState.detailError.isNullOrEmpty()
                    )


                    // Campo Fecha con selector de fecha material
                    DateSelectorDialog(
                        date = uiState.date.toString(),
                        supportingText = {
                            uiState.dateError?.let {
                                Text(it, color = Color.Red, fontSize = 12.sp)
                            }
                        },
                        isError = !uiState.dateError.isNullOrEmpty()
                    ) {
                        transactionFormViewModel.updateField("date", it)
                    }


                    Spacer(modifier = Modifier.height(16.dp))

                    // Botones
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Botón Cancelar
                        OutlinedButton(
                            onClick = onBackClick,
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 4.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Cancelar")
                        }

                        // Botón Guardar
                        Button(
                            onClick = {
                                transactionFormViewModel.onSave(
                                    transactionType = transactionType,
                                    onSaveSuccess = {
                                        Toast.makeText(
                                            context,
                                            "Transaccion guardada",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        onSaveClick()
                                    })
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 4.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Guardar")
                        }
                    }
                }
            }
        }
    }
}

