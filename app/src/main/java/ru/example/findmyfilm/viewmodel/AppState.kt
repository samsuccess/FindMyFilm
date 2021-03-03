package ru.example.findmyfilm.viewmodel

import ru.example.findmyfilm.model.NowPlaying
import ru.example.findmyfilm.model.UpComing

sealed class AppState{
    data class Success (val nowFilmData: NowPlaying, val upComFilmData: UpComing) : AppState()
    data class Error (val error: Throwable) : AppState()
    object Loading : AppState()
}
