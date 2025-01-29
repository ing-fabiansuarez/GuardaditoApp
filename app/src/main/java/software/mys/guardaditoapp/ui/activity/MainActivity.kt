package software.mys.guardaditoapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import retrofit2.Callback
import software.mys.guardaditoapp.data.network.FireBaseRealTimeApiService
import software.mys.guardaditoapp.data.network.MyApi
import software.mys.guardaditoapp.model.Account


import software.mys.guardaditoapp.ui.screen.HomeScreen
import software.mys.guardaditoapp.ui.screen.TopBar
import software.mys.guardaditoapp.ui.theme.GuardaditoAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuardaditoAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar(
                            onClickLogOut = {
                                startActivity(
                                    Intent(
                                        applicationContext,
                                        AuthActivity::class.java
                                    ).apply {
                                        flags =
                                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    })
                            }
                        )
                    }
                ) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding).padding(8.dp))

                }
            }
        }
    }
}