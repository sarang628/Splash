package com.sarang.screen_splash

interface SplashService {
    suspend fun checkSession(): Boolean
    suspend fun logout()
}