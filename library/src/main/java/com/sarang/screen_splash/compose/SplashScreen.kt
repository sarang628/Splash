package com.sarang.screen_splash.compose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.screen_splash.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    onLoginExpired: () -> Unit,
    onSuccess: () -> Unit
) {

    val uiState by splashViewModel.uiState.collectAsState()
    val coroutine = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = com.sarang.theme.R.color.colorSecondaryLight))
    ) {

        Spacer(modifier = Modifier.height(100.dp))
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "T O R A N G",
                color = colorResource(id = com.sarang.theme.R.color.colorSecondary),
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hit the spot",
                color = colorResource(id = com.sarang.theme.R.color.colorSecondary),
                fontSize = 20.sp
            )
        }

        if (uiState.isLogin == false) {
            AlertDialog(
                onDismissRequest = { },
                confirmButton = {
                    Button(onClick = {
                        coroutine.launch {
                            splashViewModel.logout()
                            onLoginExpired.invoke()
                        }
                    }) {
                        Text(text = "확인")
                    }
                },
                title = { Text(text = "Your login expired.") }
            )
        }

        if (uiState.isLogin == true) {
            onSuccess.invoke()
        }
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    val context = LocalContext.current
    SplashScreen(onLoginExpired = {
        Log.d("PreviewSplashScreen", "onLoginExpired")
    }, onSuccess = {
        Log.d("PreviewSplashScreen", "onSuccess")
    })
}