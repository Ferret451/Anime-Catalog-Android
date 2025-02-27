package bsuir.anilist.auth.model

data class User(
    var id: String = "",
    var email: String = "",
    val userInfo: UserInfo = UserInfo()
)
