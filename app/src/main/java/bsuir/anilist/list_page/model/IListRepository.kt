package bsuir.anilist.list_page.model

interface IListRepository {
    fun getAnimeList(callback: (List<Anime>?) -> Unit)
    fun getAnime(id: String, callback: (Anime?) -> Unit)
}