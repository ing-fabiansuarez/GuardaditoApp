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
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Savings
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
import software.mys.guardaditoapp.ui.models.AccountUi

@Composable
fun AccountSelectorDialog(
    account: AccountUi,
    listAccounts: List<AccountUi>,
    supportingText: @Composable () -> Unit,
    isError: Boolean,
    onItemSelected: (AccountUi) -> Unit,


    ) {

    // Campo Cuenta con selector
    var showSelectorAccounts by remember { mutableStateOf(false) }
    val availableAccounts = remember {
        listAccounts
    }

    OutlinedTextField(
        value = account.name,
        onValueChange = { /* No permitir edición directa */ },
        label = { Text("Cuenta") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.AccountBalance,
                contentDescription = "Cuenta"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showSelectorAccounts = true },
        shape = RoundedCornerShape(12.dp),
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { showSelectorAccounts = true }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Seleccionar cuenta"
                )
            }
        },
        supportingText = supportingText,
        isError= isError,

    )

    // Diálogo de selección de cuentas
    if (showSelectorAccounts) {
        AlertDialog(
            onDismissRequest = { showSelectorAccounts = false },
            title = { Text("Seleccionar Cuenta") },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    availableAccounts.forEach { opcionCuenta ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    onItemSelected(opcionCuenta)
                                    showSelectorAccounts = false
                                },
                            color = if (account == opcionCuenta)
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
                                    imageVector = Icons.Default.Savings,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = opcionCuenta.name,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showSelectorAccounts = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
}