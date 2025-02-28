package bsuir.anilist.favorites_page.model

import bsuir.anilist.utils.DBPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseFavoritesRepository: IFavoriteRepository {
    private val db = FirebaseFirestore.getInstance()
    private val favoritesCollection = db.collection(DBPath.FAVORITES.path)

    override fun addToFavorites(animeId: String, userId: String, callback: (Boolean) -> Unit) {
        val userFavoritesRef = favoritesCollection.document(userId)

        userFavoritesRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    userFavoritesRef.update("animeIds", FieldValue.arrayUnion(animeId))
                        .addOnSuccessListener { callback(true) }
                        .addOnFailureListener { callback(false) }
                } else {
                    userFavoritesRef.set(mapOf("animeIds" to listOf(animeId)))
                        .addOnSuccessListener { callback(true) }
                        .addOnFailureListener { callback(false) }
                }
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    override fun deleteFromFavorites(animeId: String, userId: String, callback: (Boolean) -> Unit) {
        val userFavoritesRef = favoritesCollection.document(userId)

        userFavoritesRef.update("animeIds", FieldValue.arrayRemove(animeId))
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }

    override fun getUserFavorites(userId: String, callback: (List<String>?) -> Unit) {
        val userFavoritesRef = favoritesCollection.document(userId)

        userFavoritesRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val animeIds = document.get("animeIds") as? List<String>
                    callback(animeIds ?: emptyList())
                } else {
                    callback(emptyList())
                }
            }
            .addOnFailureListener {
                callback(null)
            }
    }
}