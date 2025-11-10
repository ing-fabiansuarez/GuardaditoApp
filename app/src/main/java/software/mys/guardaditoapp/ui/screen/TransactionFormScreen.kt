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
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import software.mys.guardaditoapp.ui.models.CategoryUiType
import software.mys.guardaditoapp.ui.models.TransactionTypeUi
import software.mys.guardaditoapp.ui.screen.components.AccountSelectorDialog
import software.mys.guardaditoapp.ui.screen.components.CategorySelectorDialog
import software.mys.guardaditoapp.ui.screen.components.DateSelectorDialog
import software.mys.guardaditoapp.ui.viewmodel.TransactionFormViewModel
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

import software.mys.guardaditoapp.ui.screen.layout.topbars.TransactionTopAppBar
import software.mys.guardaditoapp.R
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale


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
            when (transactionType) {
                TransactionTypeUi.INCOME -> TransactionTopAppBar(
                    onBackClick = onBackClick,
                    onSaveClick = {
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
                    containerColor = colorResource(id = R.color.green_income),
                    canSave = uiState.formValid,
                    title = "Nuevo Ingreso",
                )

                TransactionTypeUi.EXPENSE -> TransactionTopAppBar(
                    onBackClick = onBackClick,
                    onSaveClick = {
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
                    containerColor = colorResource(id = R.color.red_expense),
                    canSave = uiState.formValid,
                    title = "Nuevo Gasto",
                )

                TransactionTypeUi.TRANSFER -> {}
            }


        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Versión alternativa con gradiente personalizado


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
                    FormattedAmountField(
                        value = uiState.amountBigDecimal,
                        onValueChange = { newValue ->
                            transactionFormViewModel.updateField(
                                "amountBigDecimal",
                                newValue
                            )
                            transactionFormViewModel.updateField(
                                "amount",
                                formatCurrencyLive(newValue.toPlainString())
                            )
                        }
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


@Composable
fun FormattedAmountField(
    value: BigDecimal?,
    onValueChange: (BigDecimal) -> Unit
) {
    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                formatCurrencyLive(
                    value?.toPlainString() ?: ""
                )
            )
        )
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { newTextFieldValue ->
            val rawInput = newTextFieldValue.text
            val formatted = formatCurrencyLive(rawInput)

            // Calcula desplazamiento del cursor (mantiene posición natural)
            val cursorPosition =
                formatted.length - (rawInput.length - newTextFieldValue.selection.start)
            val adjustedSelection = TextRange(cursorPosition.coerceIn(0, formatted.length))

            textFieldValue = TextFieldValue(
                text = formatted,
                selection = adjustedSelection
            )

            // Devuelve el BigDecimal limpio
            onValueChange(parseCurrencyToBigDecimal(formatted))
        },
        label = { Text("Monto") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Savings,
                contentDescription = "Monto"
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    )
}

fun parseCurrencyToBigDecimal(text: String): BigDecimal {
    return try {
        // Quitamos todo excepto dígitos y punto decimal
        val clean = text.replace("[^\\d.]".toRegex(), "")
        if (clean.isEmpty()) BigDecimal.ZERO else BigDecimal(clean)
    } catch (e: Exception) {
        BigDecimal.ZERO
    }
}


fun formatCurrencyLive(input: String): String {
    if (input.isBlank()) return ""

    // Quita todo lo que no sea número o punto decimal
    val clean = input.replace("[^\\d.]".toRegex(), "")
    if (clean.isBlank()) return ""

    // Si termina con ".", deja que el usuario siga escribiendo
    if (clean.endsWith(".")) return "$" + clean

    return try {
        val symbols = DecimalFormatSymbols(Locale("es", "MX")).apply {
            groupingSeparator = ','
            decimalSeparator = '.'
        }
        val number = clean.toDouble()
        val formatter = DecimalFormat("$#,###.##", symbols)
        formatter.format(number)
    } catch (e: Exception) {
        "$" + clean
    }
}