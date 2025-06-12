package software.mys.guardaditoapp.ui.screen.form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import software.mys.guardaditoapp.ui.models.AccountUi
import software.mys.guardaditoapp.ui.viewmodel.AccountFormViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AccountForm(
    onCloseClick: () -> Unit = {},
    onSaveComplete: (AccountUi) -> Unit = {},
    account: AccountUi = AccountUi()
) {

    val viewModel: AccountFormViewModel = viewModel()
    viewModel.loadCategory(account)

    ModalBottomSheet(
        onDismissRequest = onCloseClick
    ) {
        val uiState by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("Cuenta ${uiState.accountUi.name}", style = MaterialTheme.typography.titleLarge)

            // Campo Nombre
            OutlinedTextField(
                value = uiState.accountUi.name,
                onValueChange = viewModel::setName,
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Campo Tipo
            OutlinedTextField(
                value = uiState.accountUi.type,
                onValueChange = viewModel::setType,
                label = { Text("Tipo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Campo Saldo
            OutlinedTextField(
                value = uiState.balanceTxt,
                onValueChange = viewModel::setBalanceText,
                label = { Text("Saldo Inicial") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            // Selector de Color
            InputColor(
                onColorSelected = viewModel::setColor,
                selectedColor = uiState.accountUi.colorHex
            )


            // Botón Guardar
            Button(
                onClick = {
                    onSaveComplete(
                        uiState.accountUi
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