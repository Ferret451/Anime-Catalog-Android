package bsuir.anilist.auth.model

interface IUserRepository {
    suspend fun createAccount(email: String, password: String): Pair<User, String>
    suspend fun signIn(email: String, password: String): Pair<User, String>
    fun isAuthed(): Boolean
}