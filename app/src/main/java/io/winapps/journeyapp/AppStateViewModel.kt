package io.winapps.journeyapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AppStateViewModel : ViewModel() {
    var userId by mutableStateOf<String?>(null)
    var username by mutableStateOf<String?>(null)
    var apiKey by mutableStateOf<String?>(null)
    var jwt by mutableStateOf<String?>(null)
    var isLoggedIn by mutableStateOf(false)
    var didFinishSplash by mutableStateOf(false)

    // Additional state variables (selectedEntry, idLocations, etc.) can be added as needed.
}