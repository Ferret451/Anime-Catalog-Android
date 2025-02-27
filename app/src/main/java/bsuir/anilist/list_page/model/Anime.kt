package bsuir.anilist.list_page.model

data class Anime(
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var studio: String = "",
    var seriesAmount: Int = 12,
    var imagesURL: List<String> = listOf()
)
