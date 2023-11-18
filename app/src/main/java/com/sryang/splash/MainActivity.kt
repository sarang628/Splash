package com.sryang.splash

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sryang.splash.compose.SplashScreen
import com.sryang.torang_repository.repository.LoginRepository
import com.sryang.torang_repository.repository.LoginRepositoryTest
import com.sryang.torang_repository.session.SessionService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var loginRepository: LoginRepository

    @Inject
    lateinit var sessionService: SessionService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val coroutine = rememberCoroutineScope()
            var loginState by remember { mutableStateOf("") }
            Column {
                Column(Modifier.weight(0.6f)) {
                    SplashScreen(onLoginState = {
                        Log.d("MainActivity", it.toString())
                        loginState = it?.name ?: "NONE"
                    })
                }
                Column(Modifier.weight(0.1f).background(Color.LightGray).fillMaxWidth()) {
                    Text(text = loginState)
                }
                Column(Modifier.weight(0.3f)) {
                    LoginRepositoryTest(loginRepository = loginRepository)
                    Button(onClick = {
                        coroutine.launch {
                            sessionService.saveToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzIiwic3ViIjoiYWFhIiwiaWF0IjoxNjk4Mjk3ODc3LCJleHAiOjE2OTgzMTk0Nzd9.sEF6Vc-cMxVcyCwqd5lWB7-LqPd8i2qyk-o_CVnI_Ww")
                        }
                    }) {
                        Text(text = "만료 토큰 저장")
                    }
                }
            }
        }
    }
}