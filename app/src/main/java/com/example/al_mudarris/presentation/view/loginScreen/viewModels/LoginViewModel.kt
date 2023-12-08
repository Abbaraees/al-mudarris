package com.example.al_mudarris.presentation.view.loginScreen.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.al_mudarris.presentation.view.loginScreen.screenEvents.LoginEvent
import com.example.al_mudarris.presentation.view.loginScreen.screenStates.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Authenticate -> {
                if (state.value.password == event.currentPassword) {
                    state.value.authenticated = true
                } else {
                    state.value.incorrectPassword = true
                }
            }
            is LoginEvent.SetPassword -> {
                _state.update { it.copy(
                    password = event.password
                ) }
            }
        }
    }
}