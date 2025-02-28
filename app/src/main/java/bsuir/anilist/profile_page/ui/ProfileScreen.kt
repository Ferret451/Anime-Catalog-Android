package bsuir.anilist.profile_page.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import bsuir.anilist.auth.viewmodel.AuthViewModel
import bsuir.anilist.navigation.Screen
import bsuir.anilist.profile_page.model.UserInfo
import bsuir.anilist.profile_page.viewmodel.ProfileViewModel
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel,
    navController: NavController
) {
    val userInfo by profileViewModel.userInfo.collectAsState()
    val errorMessage by profileViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.loadUserInfo()
    }

    if (errorMessage.isNotEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = errorMessage, color = Color.Red)
        }
    } else {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(userInfo.avatarURL),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            var nickname by rememberSaveable { mutableStateOf(userInfo.nickname) }
            var avatarUrl by rememberSaveable { mutableStateOf(userInfo.avatarURL) }
            var status by rememberSaveable { mutableStateOf(userInfo.status) }
            var firstName by rememberSaveable { mutableStateOf(userInfo.firstname) }
            var lastName by rememberSaveable { mutableStateOf(userInfo.lastname) }
            var gender by rememberSaveable { mutableStateOf(userInfo.gender) }
            var description by rememberSaveable { mutableStateOf(userInfo.description) }
            var birthday by rememberSaveable { mutableStateOf(userInfo.birthday) }
            var phoneNumber by rememberSaveable { mutableStateOf(userInfo.phoneNumber) }

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                item {
                    Text(text = "Email: ${authViewModel.getCurrentUser().email}")
                    InfoTextField(label = "Nickname", value = nickname, onValueChange = { nickname = it })
                    InfoTextField(label = "Avatar URL", value = avatarUrl, onValueChange = { avatarUrl = it })
                    InfoTextField(label = "Status", value = status, onValueChange = { status = it })
                    InfoTextField(label = "First Name", value = firstName, onValueChange = { firstName = it })
                    InfoTextField(label = "Last Name", value = lastName, onValueChange = { lastName = it })
                    InfoTextField(label = "Gender", value = gender, onValueChange = { gender = it })
                    InfoTextField(label = "Description", value = description, onValueChange = { description = it })
                    InfoTextField(label = "Birthday", value = birthday, onValueChange = { birthday = it })
                    InfoTextField(label = "Phone Number", value = phoneNumber, onValueChange = { phoneNumber = it })

                    Button(onClick = {
                        profileViewModel.updateUserInfo(
                            UserInfo(nickname, avatarUrl, status, firstName, lastName, gender, description, birthday, phoneNumber)
                        )
                    }, modifier = Modifier.fillMaxWidth()) { Text("Save changes") }

                    Button(
                        onClick = {
                            authViewModel.viewModelScope.launch {
                                authViewModel.signOut()
                                navController.navigate((Screen.AUTH.route))
                            }
                         },
                        modifier = Modifier.fillMaxWidth()) { Text("Quit") }
                }
            }
        }
    }
}

