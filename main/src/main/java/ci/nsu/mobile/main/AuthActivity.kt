package ci.nsu.mobile.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material3.TextButton
import androidx.wear.compose.material3.TextButtonDefaults
import ci.nsu.mobile.main.ui.theme.PracticeTheme
import viewmodel.AuthViewModel

class AuthActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {Text("Авторизация")},
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.DarkGray,
                                titleContentColor = Color.White
                            )
                        )
                    })
                { innerPadding ->
                    AuthScreenActivity(
                        modifier = Modifier.padding(innerPadding)

                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreenActivity(modifier: Modifier = Modifier
    .background(Color.Gray)) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel()
    val login by viewModel.login.collectAsState()
    val password by viewModel.password.collectAsState()
    val isLoader by viewModel.isLoader.collectAsState()
    val authAllowed by viewModel.authAllowed.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Логин *")

            TextField(
                value = login,
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp),
                placeholder = { Text("Введите логин") },
                onValueChange = {
                    viewModel.updateLogin(it)
                }
            )

            Text(text = "Пароль *")
            TextField(
                value = password,
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp),
                placeholder = { Text("Введите пароль") },
                onValueChange = {
                    viewModel.updatePassword(it)
                })
            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    modifier = modifier.padding(top = 8.dp)
                )
            }

            LaunchedEffect(authAllowed) {
                if(authAllowed){
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    (context as Activity).finish()
                }
            }

            if (isLoader) CircularProgressIndicator()

            Button(
                onClick = {
                    viewModel.login()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Magenta
                ),
                enabled = !isLoader,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(300.dp)
            ) {
                Text("Войти")
            }

            TextButton(
                onClick = {
                    //TODO: сделать RegisterActivity
                    // val intent = Intent(context, RegisterActivity::class.java)
                    // context.startActivity(intent)
                },
                colors = TextButtonDefaults.textButtonColors(
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(300.dp)
            ) {
                Text("Нет аккаунта? Зарегистрироваться")

            }
        }

    }





@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    PracticeTheme {
        AuthScreenActivity()
    }
}