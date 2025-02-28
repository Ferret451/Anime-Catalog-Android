package bsuir.anilist.profile_page.model

interface IProfileRepository {
    fun updateUserInfo(userId: String, userInfo: UserInfo, callback: (Boolean) -> Unit)
    fun getUserInfo(userId: String, callback: (UserInfo?) -> Unit)
}