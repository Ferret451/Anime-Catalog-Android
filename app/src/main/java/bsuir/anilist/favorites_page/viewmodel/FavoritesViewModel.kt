package bsuir.anilist.favorites_page.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bsuir.anilist.auth.viewmodel.AuthViewModel
import bsuir.anilist.favorites_page.model.FirebaseFavoritesRepository
import bsuir.anilist.favorites_page.model.IFavoriteRepository
import bsuir.anilist.list_page.model.Anime
import bsuir.anilist.list_page.viewmodel.ListViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FavoritesViewModel(authViewModel: AuthViewModel, listViewModel: ListViewModel): ViewModel() {
    private val _repository: IFavoriteRepository = FirebaseFavoritesRepository()
    private val _authViewModel: AuthViewModel = authViewModel
    private val _listViewModel: ListViewModel = listViewModel

    private val _favoritesList = MutableStateFlow<List<Anime>>(emptyList())
    val favoritesList = _favoritesList.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun addToFavorites() {
        val anime = _listViewModel.getCurrentAnime()
        val user = _authViewModel.getCurrentUser()

        _repository.addToFavorites(anime.id, user.id) { isAdded ->
            if (isAdded) {
                loadUserFavorites()
            } else {
                _errorMessage.value = "Error adding to favorite!"
            }
        }
    }

    fun deleteFromFavorites() {
        val anime = _listViewModel.getCurrentAnime()
        val user = _authViewModel.getCurrentUser()

        _repository.deleteFromFavorites(anime.id, user.id) { isDeleted ->
            if (isDeleted) {
                loadUserFavorites()
            } else {
                _errorMessage.value = "Error deleting to favorite!"
            }
        }
    }

    fun loadUserFavorites() {
        clearFavorites()

        _repository.getUserFavorites(_authViewModel.getCurrentUser().id) { result ->
            if (result != null) {
                viewModelScope.launch {
                    val animeList = mutableListOf<Anime>()

                    for (animeId in result) {
                        val anime = fetchAnime(animeId)
                        if (anime != null) {
                            animeList.add(anime)
                            _favoritesList.value = animeList.toList()
                        }
                    }
                }
            } else {
                _errorMessage.value = "Error loading list!"
            }
        }
    }

    fun clearFavorites() {
        _favoritesList.value = emptyList()
    }

    private suspend fun fetchAnime(animeId: String): Anime? {
        return suspendCoroutine { continuation ->
            _listViewModel.loadAnime(animeId) { anime ->
                continuation.resume(anime)
            }
        }
    }
}