package com.sryang.splash.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sryang.splash.uistate.LoginState
import com.sryang.splash.uistate.SplashUiState
import com.sryang.splash.viewmodel.SplashViewModel

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    onLoginState: (LoginState?) -> Unit
) {
    val uiState by splashViewModel.uiState.collectAsState()
    SplashScreen(
        uiState = uiState,
        onLoginState = onLoginState,
        onLogout = { splashViewModel.logout() },
        onRetry = { splashViewModel.checkLogin() })
}

@Composable
fun SplashScreen(
    uiState: SplashUiState,
    onLoginState: (LoginState?) -> Unit,
    onLogout: () -> Unit,
    onRetry: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(100.dp))
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "T O R A N G", fontSize = 45.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Hit the spot", fontSize = 20.sp)
            }

        }

        if (uiState.loginState == null) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
        //
        else if (uiState.loginState == LoginState.NETWORK_ERROR) {
            Button(
                modifier = Modifier.align(Alignment.Center),
                onClick = onRetry
            ) {
                Text(text = "Retry")
            }
        }
        //세션 만료일 경우 자체적으로 팝업 발생
        else if (uiState.loginState == LoginState.SESSION_EXPIRED) {
            AlertDialog(
                onDismissRequest = { },
                confirmButton = {
                    Button(onClick = onLogout)
                    { Text(text = "확인") }
                },
                title = { Text(text = "Your login expired.", fontSize = 16.sp) }
            )
        }
        //로그인이나 로그아웃 상태일 경우 콜백
        else {
            onLoginState.invoke(uiState.loginState)
        }
    }
}


@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen(
        uiState = SplashUiState(LoginState.NETWORK_ERROR),
        onLoginState = {},
        onLogout = {},
        onRetry = {}
    )
}