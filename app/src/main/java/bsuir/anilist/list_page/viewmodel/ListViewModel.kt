package bsuir.anilist.list_page.viewmodel

import androidx.lifecycle.ViewModel
import bsuir.anilist.list_page.model.Anime
import bsuir.anilist.list_page.model.FirestoreListRepository
import bsuir.anilist.list_page.model.IListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListViewModel : ViewModel() {
    private val _repository: IListRepository = FirestoreListRepository()
    private var _currentAnime = Anime()

    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList = _animeList.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun loadAnimeList() {
        _repository.getAnimeList { result ->
            if (result != null) {
                _animeList.value = result
            } else {
                _errorMessage.value = "Error loading list!"
            }
        }
    }

    fun loadAnime(id: String, callback: (Anime?) -> Unit) {
        _repository.getAnime(id) { result ->
            if (result != null) {
                _currentAnime = result
                callback(result)
            } else {
                _errorMessage.value = "Error loading anime!"
                callback(null)
            }
        }
    }

    fun setCurrentAnime(anime: Anime) {
        _currentAnime = anime
    }

    fun getCurrentAnime(): Anime {
        return _currentAnime
    }

    fun clearCurrentAnime() {
        _currentAnime = Anime()
    }
}