import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import software.mys.guardaditoapp.ui.models.AccountUi
import software.mys.guardaditoapp.ui.models.CategoryUi
import software.mys.guardaditoapp.ui.screen.components.AccountSelectorDialog
import software.mys.guardaditoapp.ui.screen.components.CategorySelectorDialog
import software.mys.guardaditoapp.ui.screen.components.DateSelectorDialog
import software.mys.guardaditoapp.ui.viewmodel.TransactionFormUiState
import software.mys.guardaditoapp.ui.viewmodel.TransactionFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionForm(
    title: @Composable () -> Unit,
    onDismissRequest: () -> Unit = {},
    onSaveClick: (TransactionFormUiState) -> Unit,
    listCategories: List<CategoryUi>,
    listAccounts: List<AccountUi>
) {

    var transactionFormViewModel: TransactionFormViewModel = viewModel()
    val uiState by transactionFormViewModel.uiState.collectAsState()

    Dialog(onDismissRequest = onDismissRequest) {
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
                title()
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
                    listAccounts = listAccounts,
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
                    listCategories = listCategories,
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
                        onClick = onDismissRequest,
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
                            transactionFormViewModel.onSave(onSaveSuccesful = {
                                onSaveClick(it)
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