package software.mys.guardaditoapp.auth.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth
import software.mys.guardaditoapp.MainActivity
import software.mys.guardaditoapp.ui.theme.GuardaditoAppTheme

class AuthActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Validar autenticación del usuario
        checkUserAuthentication()
        enableEdgeToEdge()
        setContent {
            GuardaditoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding)) {
                        navigateToMainActivity()
                    }
                }
            }
        }
    }

    private fun checkUserAuthentication() {
        // Usar FirebaseAuth para verificar al usuario actual
        val currentUser = auth.currentUser

        if (currentUser != null) {
            // Usuario autenticado, navegar a la pantalla principal
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
}