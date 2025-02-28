package bsuir.anilist.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import bsuir.anilist.auth.viewmodel.AuthViewModel
import bsuir.anilist.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(authViewModel: AuthViewModel, navController: NavController) {
    var isSignUpForm by rememberSaveable { mutableStateOf(true) }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordCheck by rememberSaveable { mutableStateOf("") }
    val user by authViewModel.user.collectAsState()
    val errorMessage by authViewModel.errorMessage.collectAsState()

    if (authViewModel.isAuthed()) {
        authViewModel.updateCurrentUser()
        navController.navigate(Screen.MAIN.route)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Button(
                onClick = { isSignUpForm = !isSignUpForm },
                shape = RoundedCornerShape(12.dp),
                enabled = !isSignUpForm
            ) {
                Text(text = "Sign up", fontSize = 16.sp)
            }
            Button(
                onClick = { isSignUpForm = !isSignUpForm },
                shape = RoundedCornerShape(12.dp),
                enabled = isSignUpForm
            ) {
                Text(text = "Sign in", fontSize = 16.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(0.8f),
                visualTransformation = PasswordVisualTransformation(),
            )

            if (isSignUpForm) {
                OutlinedTextField(
                    value = passwordCheck,
                    onValueChange = { passwordCheck = it },
                    label = { Text("Password Check") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(0.8f),
                    visualTransformation = PasswordVisualTransformation(),
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                authViewModel.viewModelScope.launch {
                    if (isSignUpForm) {
                        authViewModel.createAccount(email, password, passwordCheck)
                    } else {
                        authViewModel.signIn(email, password)
                    }

                    if (authViewModel.isAuthed()) {
                        navController.navigate(Screen.MAIN.route)
                    }
                }
            },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Continue", fontSize = 16.sp)
        }
    }
}

