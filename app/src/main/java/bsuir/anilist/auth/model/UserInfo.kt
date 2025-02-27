package bsuir.anilist.auth.model

data class UserInfo(
    var nickname: String = "",
    var birthday: String = "",
    var description: String = "",
    var status: String = "",
    var gender: Gender = Gender.Unknown,
    var firstname: String = "",
    var lastname: String = "",
    var phoneNumber: String = ""
)
