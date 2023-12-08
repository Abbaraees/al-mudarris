package com.example.al_mudarris.presentation.view.loginScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.al_mudarris.R
import com.example.al_mudarris.presentation.view.components.InputField
import com.example.al_mudarris.presentation.view.loginScreen.screenEvents.LoginEvent
import com.example.al_mudarris.presentation.view.loginScreen.screenStates.LoginState
import com.example.al_mudarris.ui.theme.MyGreen
import com.example.al_mudarris.ui.theme.MyRed

@Composable
fun LoginScreen(
    navController: NavHostController? = null,
    username: String = "",
    state: State<LoginState>,
    onEvent: (LoginEvent) -> Unit,
    authenticate: () -> Unit
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("almudarris.prefs", Context.MODE_PRIVATE)
    val teacherName = sharedPreferences.getString("teacherName", "")
    val currentPassword = sharedPreferences.getString("password", "")

    if (state.value.authenticated) {
        authenticate()
    }
    if (state.value.authenticated) {
        navController?.navigate("main")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Welcome\n${teacherName}",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MyGreen,
            modifier = Modifier
                .padding(top = 20.dp, bottom = 50.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.teacher),
            contentDescription = "User Picture"
        )

        InputField(
            label = "password",
            placeholder = "Enter login password" ,
            leadingIcon = Icons.Default.Lock,
            value = state.value.password,
            isPasswordField = true) {onEvent(LoginEvent.SetPassword(it))
        }

        Button(
            onClick = {
                if (currentPassword != null) {
                    if (currentPassword == state.value.password) {
                        authenticate()
                    }
                    else {
                        Toast.makeText(context, "Incorrect password!", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MyRed
            ),
            modifier = Modifier
                .padding(top = 20.dp)

        ) {
            Text(text = "Login")
        }
    }
}


//@Preview(showSystemUi = true)
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen() {
//    }
//}

