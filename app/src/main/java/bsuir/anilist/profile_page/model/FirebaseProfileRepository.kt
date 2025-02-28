package bsuir.anilist.profile_page.model

import bsuir.anilist.auth.model.User
import bsuir.anilist.list_page.model.Anime
import bsuir.anilist.utils.DBPath
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseProfileRepository: IProfileRepository {
    private val db = FirebaseFirestore.getInstance()
    private val userInfoCollection = db.collection(DBPath.USER_INFO.path)

    override fun updateUserInfo(userId: String, userInfo: UserInfo, callback: (Boolean) -> Unit) {
        val userInfoRef = userInfoCollection.document(userId)

        val updates = mapOf(
            "nickname" to userInfo.nickname,
            "avatarURL" to userInfo.avatarURL,
            "status" to userInfo.status,
            "firstname" to userInfo.firstname,
            "lastname" to userInfo.lastname,
            "gender" to userInfo.gender,
            "description" to userInfo.description,
            "birthday" to userInfo.birthday,
            "phoneNumber" to userInfo.phoneNumber
        )

        userInfoRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    userInfoRef.update(updates)
                        .addOnSuccessListener { callback(true) }
                        .addOnFailureListener { callback(false) }
                } else {
                    userInfoRef.set(updates)
                        .addOnSuccessListener { callback(true) }
                        .addOnFailureListener { callback(false) }
                }
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    override fun getUserInfo(userId: String, callback: (UserInfo?) -> Unit) {
        val userInfoRef = userInfoCollection.document(userId)

        userInfoRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val userInfo = document.toObject(UserInfo::class.java)
                    callback(userInfo)
                } else {
                    callback(UserInfo())
                }
            }
            .addOnFailureListener {
                callback(null)
            }
    }
}