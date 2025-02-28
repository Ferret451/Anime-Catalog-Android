package bsuir.anilist.profile_page.model

data class UserInfo(
    var nickname: String = "",
    var avatarURL: String = "",
    var status: String = "",
    var firstname: String = "",
    var lastname: String = "",
    var gender: String = "",
    var description: String = "",
    var birthday: String = "",
    var phoneNumber: String = ""
)
