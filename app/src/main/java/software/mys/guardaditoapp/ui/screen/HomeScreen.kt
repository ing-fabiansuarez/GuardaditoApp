package software.mys.guardaditoapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import software.mys.guardaditoapp.model.Account
import software.mys.guardaditoapp.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(modifier: Modifier) {

    val homeViewModel: HomeViewModel = viewModel() // Instancia del ViewModel
    val uiState by homeViewModel.uiState.collectAsState()
    Column(modifier = modifier) {
        Text(
            text = "Mis cuentas",
            modifier = Modifier
                .padding(bottom = 8.dp, top = 8.dp)
                .fillMaxWidth(),
            fontSize = 20.sp,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            uiState.forEach { account ->
                item {
                    AccountCard(account = account)
                }
            }
        }
    }

}

@Composable
fun AccountCard(account: Account) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .width(240.dp)
            .height(150.dp)
    ) {
        Box {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Parte central (nombre y balance)
                Text(
                    text = account.name,
                )
                Text(
                    text = "Balance: $${account.balance}",
                    textAlign = TextAlign.Start
                )

                // Parte inferior (marca ficticia o decoración)
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = account.type.uppercase(),
                    )
                }
            }
        }
    }
}