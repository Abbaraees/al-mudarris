package com.example.al_mudarris

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.al_mudarris.database.AlmudarrisDatabase
import com.example.al_mudarris.navigations.MainNavigation
import com.example.al_mudarris.ui.theme.AlMudarrisTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = MyViewModel()

//      Create the database instance
        val db by lazy {
            Room.databaseBuilder(
                applicationContext,
                AlmudarrisDatabase::class.java,
                "almudarris.db"
            ).build()
        }

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.loading.value
            }
        }

        super.onCreate(savedInstanceState)
        setContent {
            AlMudarrisTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation(db)
                }
            }
        }
    }
}

class MyViewModel: ViewModel() {
    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            _loading.value = false
        }

    }
}
