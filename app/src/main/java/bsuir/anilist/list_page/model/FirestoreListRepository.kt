package bsuir.anilist.list_page.model

import bsuir.anilist.utils.DBPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class FirestoreListRepository: IListRepository {
    private val db = FirebaseFirestore.getInstance()
    private val animeCollection = db.collection(DBPath.ANIME.path)

    override fun getAnimeList(callback: (List<Anime>?) -> Unit) {
        animeCollection.get()
            .addOnSuccessListener { snapshot ->
                val list = snapshot.documents.mapNotNull { it.toObject(Anime::class.java)?.copy(id = it.id) }
                callback(list)
            }
            .addOnFailureListener {
                callback(null) // В случае ошибки возвращаем null
            }
    }

    override fun getAnime(id: String, callback: (Anime?) -> Unit) {
        animeCollection.document(id).get()
            .addOnSuccessListener { document ->
                val anime = document.toObject(Anime::class.java)?.copy(id = document.id)
                callback(anime)
            }
            .addOnFailureListener {
                callback(null) // В случае ошибки возвращаем null
            }
    }
}