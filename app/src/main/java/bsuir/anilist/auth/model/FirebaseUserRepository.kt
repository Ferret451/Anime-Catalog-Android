package bsuir.anilist.auth.model

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseUserRepository : IUserRepository {
    private val auth = FirebaseAuth.getInstance()

    override suspend fun createAccount(email: String, password: String): Pair<User, String> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user

            if (firebaseUser == null) {
                Pair(User(), "Registration error")
            } else {
                Pair(
                    User(id = firebaseUser.uid, email = firebaseUser.email ?: ""),
                    ""
                )
            }
        } catch (e: Exception) {
            Pair(User(), e.localizedMessage ?: "Registration error")
        }
    }

    override suspend fun signIn(email: String, password: String): Pair<User, String> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user

            if (firebaseUser == null) {
                Pair(User(), "Authorization error")
            } else {
                Pair(
                    User(id = firebaseUser.uid, email = firebaseUser.email ?: ""),
                    ""
                )
            }
        } catch (e: Exception) {
            Pair(User(), e.localizedMessage ?: "Authorization error")
        }
    }

    override fun isAuthed(): Boolean {
        return auth.currentUser != null
    }
}