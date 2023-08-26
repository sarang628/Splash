package com.sarang.screen_splash

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(onSuccess: (Int) -> Unit) {
    Column {
        Text(text = "Hit the spot")
        Text(text = "Torang")
    }

    LaunchedEffect(key1 = "", block = {
        delay(3000)
        onSuccess.invoke(0)
    })
}

@Preview
@Composable
fun PreviewSplashScreen() {
    val context = LocalContext.current
    SplashScreen(onSuccess = {
        Toast.makeText(context, "onSuccess", Toast.LENGTH_SHORT).show()
    })
}