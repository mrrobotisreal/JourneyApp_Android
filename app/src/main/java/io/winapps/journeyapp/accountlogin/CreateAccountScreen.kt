package io.winapps.journeyapp.accountlogin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.winapps.journeyapp.AppStateViewModel
import io.winapps.journeyapp.ui.theme.NexaScript
import io.winapps.journeyapp.viewmodels.PasswordViewModel
import io.winapps.journeyapp.viewmodels.UsernameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(appState: AppStateViewModel, usernameViewModel: UsernameViewModel = viewModel(), passwordViewModel: PasswordViewModel = viewModel()) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var isLoginVisible by remember { mutableStateOf(false) } // Toggle between login and create

    Box(modifier = Modifier.fillMaxSize()) {
        // Centered box that mimics the iOS "modal" style
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .widthIn(max = 340.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),
            border = BorderStroke(2.dp, Color(0xFF024873)),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF88DFF2))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Title Text
                Text(
                    text = if (isLoginVisible) "Login" else "Create Account",
                    fontFamily = NexaScript,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                    color = Color(0xFF022840),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Username TextField
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username", fontFamily = NexaScript,
                        fontWeight = FontWeight.Light, color = Color(0xFF022840)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        focusedTextColor = Color(0xFF022840),
                        unfocusedTextColor = Color(0xFF022840),
                        focusedBorderColor = Color(0xFF024873),
                        unfocusedBorderColor = Color(0xFF024873),
                        cursorColor = Color(0xFF022840),
                        focusedLabelColor = Color(0xFF022840),
                        unfocusedLabelColor = Color(0xFF022840)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Password TextField
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password", fontFamily = NexaScript,
                        fontWeight = FontWeight.Light, color = Color(0xFF022840)) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        focusedTextColor = Color(0xFF022840),
                        unfocusedTextColor = Color(0xFF022840),
                        focusedBorderColor = Color(0xFF024873),
                        unfocusedBorderColor = Color(0xFF024873),
                        cursorColor = Color(0xFF022840),
                        focusedLabelColor = Color(0xFF022840),
                        unfocusedLabelColor = Color(0xFF022840)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Error message (if any)
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        fontFamily = NexaScript,
                        fontWeight = FontWeight.Light,
                        color = Color.Red,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Bottom row with "toggle login" button and "create / login" button
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = { isLoginVisible = !isLoginVisible }
                    ) {
                        Text(
                            text = if (isLoginVisible) "Need an account?" else "Already have an account?",
                            fontFamily = NexaScript,
                            fontWeight = FontWeight.Light,
                            fontSize = 16.sp,
                            color = Color(0xFF024873)
                        )
                    }

                    if (isLoading) {
                        CircularProgressIndicator(color = Color(0xFF024873))
                    } else {
                        Button(
                            onClick = {
                                // Validate fields
                                if (username.isBlank() || password.isBlank()) {
                                    errorMessage = "Username and password cannot be empty"
                                    return@Button
                                }
                                // Here you’d call your suspend functions to create an account or log in.
                                // For demonstration, we simply mark the user as logged in.
                                appState.username = username
                                appState.isLoggedIn = true
                            },
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(2.dp, Color(0xFF022840)),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A8CBF))
                        ) {
                            Text(
                                text = if (isLoginVisible) "Log In" else "Create",
                                fontFamily = NexaScript,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }

//    var username by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var errorMessage by remember { mutableStateOf("") }
//    var isLoading by remember { mutableStateOf(false) }
//    var isLoginVisible by remember { mutableStateOf(false) } // Toggle between login and create
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = if (isLoginVisible) "Login" else "Create Account",
//            fontSize = 32.sp,
//            color = Color(0xFF022840),
//            modifier = Modifier.padding(vertical = 8.dp)
//        )
//        OutlinedTextField(
//            value = username,
//            onValueChange = { username = it },
//            label = { Text("Username") },
//            singleLine = true,
//            modifier = Modifier.fillMaxWidth(),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Password") },
//            singleLine = true,
//            visualTransformation = PasswordVisualTransformation(),
//            modifier = Modifier.fillMaxWidth(),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            TextButton(onClick = { isLoginVisible = !isLoginVisible }) {
//                Text(
//                    text = if (isLoginVisible) "Need an account?" else "Already have an account?",
//                    fontSize = 16.sp,
//                    color = Color(0xFF024873)
//                )
//            }
//            if (isLoading) {
//                CircularProgressIndicator(color = Color(0xFF024873))
//            } else {
//                Button(
//                    onClick = {
//                        // Validate fields
//                        if (username.isBlank() || password.isBlank()) {
//                            errorMessage = "Username and password cannot be empty"
//                            return@Button
//                        }
//                        // Here you’d call your suspend functions to create an account or log in.
//                        // For demonstration, we simply mark the user as logged in.
//                        appState.username = username
//                        appState.isLoggedIn = true
//                    },
//                    colors = buttonColors(containerColor = Color(0xFF0A8CBF)) // was previously backgroundColor, but it didn't exist. Looking into it!
//                ) {
//                    Text(
//                        text = if (isLoginVisible) "Log In" else "Create",
//                        color = Color.White
//                    )
//                }
//            }
//        }
//        if (errorMessage.isNotEmpty()) {
//            Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
//        }
//    }
}