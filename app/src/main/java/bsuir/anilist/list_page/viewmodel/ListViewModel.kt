package bsuir.anilist.list_page.viewmodel

import androidx.lifecycle.ViewModel
import bsuir.anilist.auth.model.User
import bsuir.anilist.list_page.model.Anime
import bsuir.anilist.list_page.model.FirestoreListRepository
import bsuir.anilist.list_page.model.IListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListViewModel : ViewModel() {
    private val _repository: IListRepository = FirestoreListRepository()

    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList = _animeList.asStateFlow()

    private val _currentAnime = MutableStateFlow(Anime())
    val currentAnime = _currentAnime.asStateFlow()

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

    fun loadAnime(id: String) {
        _repository.getAnime(id) { result ->
            if (result != null) {
                _currentAnime.value = result
            } else {
                _errorMessage.value = "Error loading anime!"
            }
        }
    }
}