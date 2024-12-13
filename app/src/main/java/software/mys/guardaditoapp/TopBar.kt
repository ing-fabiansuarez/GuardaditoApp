package software.mys.guardaditoapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(topBarViewModel: TopBarViewModel = viewModel()) {

    val topBarUiState by topBarViewModel.uiState.collectAsState()

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    LargeTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Column {
                Text(
                    "Welcome ${topBarUiState.userSesion.name}!",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                AsyncImage(
                    model = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                    contentDescription = "User image",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)// Make the image circular
                        .border(2.dp, Color.Black, CircleShape),
                    contentScale = ContentScale.Crop // Crop the image to fit the circle
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Localized description"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}