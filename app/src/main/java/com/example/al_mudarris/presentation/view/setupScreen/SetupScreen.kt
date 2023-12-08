package com.example.al_mudarris.presentation.view.setupScreen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.al_mudarris.presentation.view.components.InputField
import com.example.al_mudarris.presentation.view.setupScreen.screenEvents.SetupEvent
import com.example.al_mudarris.navigations.Login
import com.example.al_mudarris.presentation.view.setupScreen.screenStates.SetupState
import com.example.al_mudarris.ui.theme.MyGreen
import com.example.al_mudarris.ui.theme.MyRed
import kotlin.reflect.KFunction1

@Composable
fun SetupScreen(
    navController: NavHostController,
    state: State<SetupState>,
    onEvent: KFunction1<SetupEvent, Unit>
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("almudarris.prefs", Context.MODE_PRIVATE)
    var isChecked by rememberSaveable {
        mutableStateOf(false)
    }
    if (state.value.isSettingUp) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Setup Your App",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MyGreen,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 50.dp)
            )

            InputField(
                label = "name",
                placeholder = "Enter your name",
                leadingIcon = Icons.Default.AccountCircle,
                value = state.value.teacherName
            ) {
                onEvent(SetupEvent.SetTeacherName(it))
            }

            InputField(
                label = "school",
                placeholder = "Enter your school name",
                leadingIcon = Icons.Default.Home,
                value = state.value.schoolName
            ) {
                onEvent(SetupEvent.SetSchoolName(it))
            }

            InputField(
                label = "school",
                placeholder = "Enter your class name",
                leadingIcon = Icons.Default.Home,
                value = state.value.className
            ) {
                onEvent(SetupEvent.SetClassName(it))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(.75f)

            ) {
                Checkbox(
                    checked = isChecked, onCheckedChange = { isChecked = it },
                )
                Text(
                    text = "Enable password login",
                    modifier = Modifier.clickable { isChecked = !isChecked }
                )
            }

            InputField(
                label = "school",
                placeholder = "Enter login password",
                leadingIcon = Icons.Default.Lock,
                value = state.value.password,
                isPasswordField = true
            ) {
                onEvent(SetupEvent.SetPassword(it))
            }

            Button(
                onClick = { onEvent(SetupEvent.SetupApp(sharedPreferences)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MyRed
                ),
                modifier = Modifier
                    .padding(top = 20.dp)

            ) {
                Text(text = "Finish Setup")
            }
        }
    }
    else {
        navController.navigate(Login.route)
    }
}




//@Preview(showSystemUi = true)
//@Composable
//fun SetupScreenPreview() {
//    SetupScreen()
//}
