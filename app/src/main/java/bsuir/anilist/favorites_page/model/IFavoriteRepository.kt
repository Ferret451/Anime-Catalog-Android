package bsuir.anilist.favorites_page.model

import bsuir.anilist.list_page.model.Anime

interface IFavoriteRepository {
    fun addToFavorites(animeId: String, userId: String, callback: (Boolean) -> Unit)
    fun deleteFromFavorites(animeId: String, userId: String, callback: (Boolean) -> Unit)
    fun getUserFavorites(userId: String, callback: (List<String>?) -> Unit)
}