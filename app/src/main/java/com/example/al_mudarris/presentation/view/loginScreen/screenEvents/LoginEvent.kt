package com.example.al_mudarris.presentation.view.loginScreen.screenEvents

sealed interface LoginEvent {
    data class SetPassword(val password: String): LoginEvent
    data class Authenticate(val currentPassword: String): LoginEvent
}