package software.mys.guardaditoapp.ui.screen.components.accountscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import software.mys.guardaditoapp.formatNumberFromDoubleToString
import software.mys.guardaditoapp.ui.models.AccountUi


@Composable
fun AccountItem(
    account: AccountUi,
    onDeleteClick: (AccountUi) -> Unit = {},
    onClickItem: (AccountUi) -> Unit = {}
) {
    Card {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.AccountBalanceWallet,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = Color(account.colorHex)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(account.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(account.type)
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        onClickItem(account)
                    }) {
                        Icon(Icons.AutoMirrored.Filled.NavigateNext, contentDescription = null)
                    }
                    IconButton(
                        onClick = { onDeleteClick(account) }
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Eliminar cuenta",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }

                }
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Balance: ")

                Text(
                    "$ ${formatNumberFromDoubleToString(account.balance)}",
                    fontSize = 18.sp
                )
            }
        }
    }
}