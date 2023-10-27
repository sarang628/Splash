package com.sryang.splash.usecase

interface SplashUseCase {
    suspend fun checkSession(): Boolean
    suspend fun logout()
    suspend fun isLogin(): Boolean
}