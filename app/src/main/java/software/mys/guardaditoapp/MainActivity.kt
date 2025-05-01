package software.mys.guardaditoapp

import android.os.Bundle

import androidx.activity.compose.setContent

import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import software.mys.guardaditoapp.ui.theme.GuardaditoAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuardaditoAppTheme {
                NavigationApp()
            }
        }
    }
}





