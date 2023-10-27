package com.sryang.splash.uistate

data class SplashUiState(
    val loginState: LoginState? = null
)

enum class LoginState {
    LOGOUT,
    LOGIN,
    SESSION_EXPIRED
}