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
    viewModel: AccountFormViewModel = viewModel(),
    onCloseClick: () -> Unit = {},
    onSaveComplete: (AccountUi) -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = onCloseClick
    ) {
        val uiState by viewModel.uiState

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("Nueva Cuenta", style = MaterialTheme.typography.titleLarge)
            
            // Campo Nombre
            OutlinedTextField(
                value = uiState.name,
                onValueChange = viewModel::setName,
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Campo Tipo
            OutlinedTextField(
                value = uiState.type,
                onValueChange = viewModel::setType,
                label = { Text("Tipo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Campo Saldo
            OutlinedTextField(
                value = uiState.balance,
                onValueChange = viewModel::setBalance,
                label = { Text("Saldo Inicial") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            // Selector de Color
            InputColor(
                onColorSelected = viewModel::setColor,
                selectedColor = uiState.selectedColor
            )

            // Selector de Icono
            InputIcon(
                onIconSelected = viewModel::setIcon,
                selectedIcon = uiState.selectedIcon
            )

            // Botón Guardar
            Button(
                onClick = {
                    onSaveComplete(
                        AccountUi(
                            name = uiState.name,
                            type = uiState.type,
                            balance = uiState.balance.toDoubleOrNull() ?: 0.0,
                            icon = uiState.selectedIcon,
                            colorHex = uiState.selectedColor
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