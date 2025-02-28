package bsuir.anilist.profile_page.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import bsuir.anilist.auth.viewmodel.AuthViewModel

@Composable
fun ProfileAvaChangeScreen(authViewModel: AuthViewModel) {
    var avaURL by rememberSaveable { mutableStateOf("") }
    val errorMessage by authViewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
            )
        }

        OutlinedTextField(
            label = { Text("Avatar URL") },
            value = avaURL,
            onValueChange = { avaURL = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
            Text("Confirm")
        }
        Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
            Text("Cancel")
        }
    }
}