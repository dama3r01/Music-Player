package com.example.musicplayer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayer.data.FavoriteSong
import com.example.musicplayer.data.Playlist
import com.example.musicplayer.data.PlaylistDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val playlistDao: PlaylistDao
) : ViewModel() {

    val playlists = playlistDao.getAllPlaylists()
    val favoriteSongIds = playlistDao.getFavoriteSongIds()

    fun createPlaylist(name: String) {
        viewModelScope.launch {
            playlistDao.insertPlaylist(Playlist(name = name))
        }
    }

    fun toggleFavorite(songId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                playlistDao.removeFavorite(songId)
            } else {
                playlistDao.addFavorite(FavoriteSong(songId))
            }
        }
    }
}
