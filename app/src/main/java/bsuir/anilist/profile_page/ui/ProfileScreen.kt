package bsuir.anilist.profile_page.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bsuir.anilist.auth.viewmodel.AuthViewModel
import bsuir.anilist.navigation.Screen
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
) {
    val user by authViewModel.user.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(user.userInfo.avatarURL),
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Text(text = "Email: ${user.email}")
                InfoTextField(label = "Nickname", value = user.userInfo.nickname, onValueChange = { user.userInfo.nickname = it })
                InfoTextField(label = "Avatar URL", value = user.userInfo.avatarURL, onValueChange = { user.userInfo.avatarURL = it })
                InfoTextField(label = "Status", value = user.userInfo.status, onValueChange = { user.userInfo.status = it })
                InfoTextField(label = "First Name", value = user.userInfo.firstname, onValueChange = { user.userInfo.firstname = it })
                InfoTextField(label = "Last Name", value = user.userInfo.lastname, onValueChange = { user.userInfo.lastname = it })
                InfoTextField(label = "Gender", value = user.userInfo.gender, onValueChange = { user.userInfo.gender = it })
                InfoTextField(label = "Description", value = user.userInfo.description, onValueChange = { user.userInfo.description = it })
                InfoTextField(label = "Birthday", value = user.userInfo.birthday, onValueChange = { user.userInfo.birthday = it })
                InfoTextField(label = "Phone Number", value = user.userInfo.phoneNumber, onValueChange = { user.userInfo.phoneNumber = it })
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {  }, modifier = Modifier.fillMaxWidth()) { Text("Save changes") }
                Button(onClick = {  }, modifier = Modifier.fillMaxWidth()) { Text("Quit") }
            }
        }
    }
}

