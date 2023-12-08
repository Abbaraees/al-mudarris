package com.example.al_mudarris.navigations

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.al_mudarris.presentation.view.dashboardScreen.DashboardScreen
import com.example.al_mudarris.presentation.view.loginScreen.LoginScreen
import com.example.al_mudarris.presentation.view.loginScreen.viewModels.LoginViewModel
import com.example.al_mudarris.presentation.view.setupScreen.SetupScreen
import com.example.al_mudarris.presentation.view.setupScreen.viewmodels.SetupViewModel

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("almudarris.prefs", Context.MODE_PRIVATE)
    val teacherName = sharedPreferences.getString("teacherName", "")
    var authenticated by rememberSaveable {
        mutableStateOf(false)
    }

    var startDestination = if (authenticated) "main" else "auth"
    var authDestination = if (teacherName?.isNotBlank() == true) "login" else "setup"

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(authDestination, "auth") {
            composable(Login.route) {
                val loginViewModel = LoginViewModel()
                val state = loginViewModel.state.collectAsState()
                LoginScreen(
                    navController,
                    state = state,
                    onEvent = loginViewModel::onEvent,
                ) { authenticated = true }
            }
            composable(Setup.route) {
                val setupViewModel = SetupViewModel()
                val state = setupViewModel.state.collectAsState()
                SetupScreen(navController, state=state, onEvent=setupViewModel::onEvent)
            }
        }
        navigation("dashboard", "main") {
            composable(Dashboard.route) {
                DashboardScreen()
            }
        }
    }
}