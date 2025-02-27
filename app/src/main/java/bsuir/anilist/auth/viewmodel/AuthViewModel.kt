package bsuir.anilist.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import bsuir.anilist.auth.model.FirebaseUserRepository
import bsuir.anilist.auth.model.IUserRepository
import bsuir.anilist.auth.model.User
import bsuir.anilist.auth.utils.InputValidator
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _repository: IUserRepository = FirebaseUserRepository()

    private var _user = MutableStateFlow(User())
    var user = _user.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    suspend fun createAccount(email: String, password: String, passwordCheck: String) {
        if (!validateSignInput(email, password, passwordCheck)) {
            clearError()
            return
        }

        val result = _repository.createAccount(email, password)
        _user.value = result.first
        _errorMessage.value = result.second
    }

    suspend fun signIn(email: String, password: String) {
        if (!validateSignInput(email, password)) {
            clearError()
            return
        }

        val result = _repository.signIn(email, password)
        _user.value = result.first
        _errorMessage.value = result.second
    }

    public fun isAuthed(): Boolean {
        return _repository.isAuthed()
    }

    private fun validateSignInput(email: String, password: String, passwordCheck: String = password): Boolean {
        _errorMessage.value = InputValidator.validateEmail(email)
        if (_errorMessage.value.isNotEmpty()) {
            return false
        }

        _errorMessage.value = InputValidator.validatePassword(password, passwordCheck)
        if (_errorMessage.value.isNotEmpty()) {
            return false
        }

        return true
    }

    private fun clearError() {
        viewModelScope.launch {
            delay(3000)
            _errorMessage.value = ""
        }
    }
}