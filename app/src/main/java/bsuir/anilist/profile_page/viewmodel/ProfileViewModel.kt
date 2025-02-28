package bsuir.anilist.profile_page.viewmodel

import androidx.lifecycle.ViewModel
import bsuir.anilist.auth.viewmodel.AuthViewModel
import bsuir.anilist.profile_page.model.FirebaseProfileRepository
import bsuir.anilist.profile_page.model.IProfileRepository
import bsuir.anilist.profile_page.model.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(authViewModel: AuthViewModel): ViewModel() {
    private val _repository: IProfileRepository = FirebaseProfileRepository()
    private val _authViewModel: AuthViewModel = authViewModel

    private val _userInfo = MutableStateFlow(UserInfo())
    val userInfo = _userInfo.asStateFlow()

    private val _isLoaded = MutableStateFlow(false)
    val isLoaded = _isLoaded.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun updateUserInfo(userInfo: UserInfo) {
        val currentUser = _authViewModel.getCurrentUser()
        _repository.updateUserInfo(currentUser.id, userInfo) { isUpdated ->
            if (isUpdated) {
                _userInfo.value = userInfo
            } else {
                _errorMessage.value = "Error updating!"
            }
        }
    }

    fun loadUserInfo() {
        _isLoaded.value = false
        val currentUser = _authViewModel.getCurrentUser()
        _repository.getUserInfo(currentUser.id) { userInfo ->
            if (userInfo != null) {
                _userInfo.value = userInfo
                _isLoaded.value = true
            } else {
                _errorMessage.value = "Error getting info!"
            }

        }
    }
}