package com.example.al_mudarris.presentation.view.loginScreen.screenStates

data class LoginState(
    var password: String = "",
    var authenticated: Boolean = false,
    var incorrectPassword: Boolean = false
)
