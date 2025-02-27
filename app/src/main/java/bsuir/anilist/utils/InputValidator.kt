package bsuir.anilist.utils

import android.util.Patterns

class InputValidator {
    companion object {
        fun validateEmail(email: String): String {
            if (email.isBlank()) {
                return "Email is empty!"
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return "Incorrect email format!"
            }

            return ""
        }

        fun validatePassword(password: String, passwordCheck: String): String {
            if (password.isBlank()) {
                return "Password length supposed to be from 6 symbols!"
            }

            if (password != passwordCheck) {
                return "Password check is not equal to password"
            }

            return ""
        }
    }
}