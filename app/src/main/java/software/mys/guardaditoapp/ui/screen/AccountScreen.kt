package software.mys.guardaditoapp.ui.screen

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import software.mys.guardaditoapp.formatNumberFromDoubleToString
import software.mys.guardaditoapp.ui.models.AccountUi
import software.mys.guardaditoapp.ui.screen.components.AccountItem
import software.mys.guardaditoapp.ui.screen.components.SummaryAccount
import software.mys.guardaditoapp.ui.screen.form.AccountForm
import software.mys.guardaditoapp.ui.viewmodel.AccountViewModel
import software.mys.guardaditoapp.ui.viewmodel.AccountViewModelFactory
import java.text.NumberFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(onBackClick: () -> Unit = {}) {

    val application = LocalContext.current.applicationContext as Application
    val accountViewModel: AccountViewModel =
        viewModel(factory = AccountViewModelFactory(application))
    val uiState by accountViewModel.uiState.collectAsState()
    var selectedAccount by remember { mutableStateOf(AccountUi()) }

    var showForm by remember { mutableStateOf(false) }
    if (showForm) {
        AccountForm(
            onCloseClick = { showForm = false },
            onSaveComplete = { newAccount ->
                accountViewModel.addAccount(newAccount)
                Toast.makeText(application, "Cuenta guardada", Toast.LENGTH_SHORT).show()
            },
            account = selectedAccount
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Accounts") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    OutlinedButton(
                        onClick = {
                            showForm = true
                            selectedAccount = AccountUi()
                        }
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "Agregar",
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(16.dp), // Padding alrededor de la lista
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre items
        ) {
            item { // Primer item especial para el SummaryAccount
                SummaryAccount(total = uiState.accounts.sumOf { it.balance })
                Spacer(modifier = Modifier.size(16.dp)) // Espacio después del resumen
            }

            items(
                uiState.accounts,
                key = { account -> account.id }) { account -> // Bucle sobre la lista de cuentas
                AccountItem(
                    account = account,
                    onDeleteClick = { accountToDelete ->
                        accountViewModel.deleteAccount(accountToDelete)
                        Toast.makeText(application, "Cuenta eliminada.", Toast.LENGTH_SHORT).show()
                    },
                    onClickItem = {
                        selectedAccount = it
                        showForm = true
                    }
                )
            }
        }


    }

}


@Preview() // Añade un fondo blanco para mejor visualización
@Composable
fun Preview() {
    //SummaryAccount(1000000.215)
    AccountScreen()
}
