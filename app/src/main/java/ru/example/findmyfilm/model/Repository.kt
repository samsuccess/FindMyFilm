package ru.example.findmyfilm.model

interface Repository {
    fun getNPFromLocaleStorage(): NowPlaying
    fun getNPFromServer(): NowPlaying
    fun getUpCFromLocaleStorage(): UpComing
    fun getUpCFromServer(): UpComing
}