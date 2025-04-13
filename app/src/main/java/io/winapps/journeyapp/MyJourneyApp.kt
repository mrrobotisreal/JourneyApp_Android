package io.winapps.journeyapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import androidx.navigation.compose.rememberNavController
import io.winapps.journeyapp.accountlogin.CreateAccountScreen
import io.winapps.journeyapp.maincontent.HomeScreen

@Composable
fun MyJourneyApp() {
    val appState = hiltViewModel<AppStateViewModel>()
    val navController = rememberNavController()
    val gradientColors = listOf(
        Color(0xFF022840),
        Color(0xFF024873),
        Color(0xFF0A8CBF),
        Color(0xFF88DFF2),
        Color(0xFF0A8CBF),
        Color(0xFF024873),
        Color(0xFF022840)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(gradientColors))
    ) {
        when {
            !appState.didFinishSplash -> {
                SplashScreen()
            }
            !appState.isLoggedIn -> {
                CreateAccountScreen(
                    onLoginSuccess = { appState.markLoggedIn() }
                )
            }
            else -> {
                HomeScreen() // You can later set up Navigation Compose for deeper navigation.
            }
        }
    }

    // Mimic SwiftUI's onAppear with a delay using LaunchedEffect
    LaunchedEffect(Unit) {
        delay(3500L)
        appState.didFinishSplash = true
    }
}