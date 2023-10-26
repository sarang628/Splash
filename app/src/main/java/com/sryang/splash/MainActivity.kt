package com.sryang.splash

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import com.sarang.screen_splash.compose.SplashScreen
import com.sryang.torang_repository.repository.LoginRepository
import com.sryang.torang_repository.repository.LoginRepositoryTest
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var loginRepository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Column(Modifier.weight(1f)) {
                    SplashScreen(onLoginExpired = {
                        Log.d("MainActivity", "onLoginExpired")
                    }, onSuccess = {
                        Log.d("MainActivity", "onSuccess")
                    }, unLogin = {
                        Log.d("MainActivity", "unLogin")
                    })
                }
                Column(Modifier.weight(1f)) {
                    LoginRepositoryTest(loginRepository = loginRepository)
                }
            }
        }
    }
}