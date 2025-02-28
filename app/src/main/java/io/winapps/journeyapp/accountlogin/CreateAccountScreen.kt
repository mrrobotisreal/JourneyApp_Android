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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.winapps.journeyapp.AppStateViewModel
import io.winapps.journeyapp.repository.ApiRepository
import io.winapps.journeyapp.ui.theme.NexaScript
import io.winapps.journeyapp.viewmodels.AccountViewModel
import io.winapps.journeyapp.viewmodels.AccountViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(appState: AppStateViewModel) {
    val apiRepository = ApiRepository()
    val accountViewModel: AccountViewModel = viewModel(
        factory = AccountViewModelFactory(apiRepository)
    )
    val configuration = LocalConfiguration.current
    val horizontalPadding = if (configuration.screenWidthDp >= 600) 200.dp else 10.dp

    Box(modifier = Modifier.fillMaxSize().imePadding().padding(horizontal = horizontalPadding)) {
        Card(
            modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
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
                    text = if (accountViewModel.isLoginVisible.value) "Login" else "Create Account",
                    fontFamily = NexaScript,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                    color = Color(0xFF022840),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Username TextField
                OutlinedTextField(
                    value = accountViewModel.username.value,
                    onValueChange = { accountViewModel.username.value = it },
                    label = { Text("Username", fontFamily = NexaScript,
                        fontWeight = FontWeight.Light, color = Color(0xFF022840)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false
                    ),
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

                accountViewModel.isUsernameAvailable.value?.let { available ->
                    Text(
                        text = if (available) "✅ Username is available" else "❌ Username is taken. Please choose another.",
                        color = if (available) Color.Green else Color.Red,
                        fontSize = 12.sp
                    )
                }
                if (accountViewModel.errorMessage.value.isNotEmpty()) {
                    Text(
                        text = accountViewModel.errorMessage.value,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Password TextField
                OutlinedTextField(
                    value = accountViewModel.password.value,
                    onValueChange = { accountViewModel.password.value = it },
                    label = { Text("Password", fontFamily = NexaScript,
                        fontWeight = FontWeight.Light, color = Color(0xFF022840)) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false
                    ),
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
                if (accountViewModel.errorMessage.value.isNotEmpty()) {
                    Text(
                        text = accountViewModel.errorMessage.value,
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
                        onClick = { accountViewModel.toggleIsLoginVisible() }
                    ) {
                        Text(
                            text = if (accountViewModel.isLoginVisible.value) "Need an account?" else "Already have an account?",
                            fontFamily = NexaScript,
                            fontWeight = FontWeight.Light,
                            fontSize = 16.sp,
                            color = Color(0xFF024873)
                        )
                    }

                    if (accountViewModel.isLoading.value) {
                        CircularProgressIndicator(color = Color(0xFF024873))
                    } else {
                        if (accountViewModel.isLoginVisible.value) {
                            Button(
                                onClick = { accountViewModel.login() },
                                colors = buttonColors(containerColor = Color(0xFF0A8CBF)),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(2.dp, Color(0xFF022840)),
                            ) {
//                                Text("Log In", color = Color.White, fontFamily = NexaScript, fontWeight = FontWeight.ExtraBold)
                                if (accountViewModel.showSuccessMessage.value) {
                                    Text("Logged In!!!", color = Color.White, fontFamily = NexaScript, fontWeight = FontWeight.ExtraBold)
                                } else {
                                    Text("Log In", color = Color.White, fontFamily = NexaScript, fontWeight = FontWeight.ExtraBold)
                                }
                            }
                        } else {
                            Button(
                                onClick = { accountViewModel.createAccount() },
                                colors = buttonColors(containerColor = Color(0xFF0A8CBF)),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(2.dp, Color(0xFF022840)),
                            ) {
                                if (accountViewModel.showSuccessMessage.value) {
                                    Text("Created!!!", color = Color.Green, fontFamily = NexaScript, fontWeight = FontWeight.ExtraBold)
                                } else {
                                    Text("Create", color = Color.White, fontFamily = NexaScript, fontWeight = FontWeight.ExtraBold)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}