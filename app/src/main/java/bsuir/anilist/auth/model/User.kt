package bsuir.anilist.auth.model

import bsuir.anilist.profile_page.model.UserInfo

data class User(
    var id: String = "",
    var email: String = "",
    var avatarURL: String = "",
)
