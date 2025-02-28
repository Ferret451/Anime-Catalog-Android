package bsuir.anilist.profile_page.model

data class UserInfo(
    var nickname: String = "",
    var status: String = "",
    var firstname: String = "",
    var lastname: String = "",
    var gender: Gender = Gender.Unknown,
    var description: String = "",
    var birthday: String = "",
    var phoneNumber: String = ""
)
