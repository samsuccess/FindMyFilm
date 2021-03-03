package ru.example.findmyfilm.model

class RepositoryImpl: Repository{
    override fun getNPFromLocaleStorage(): NowPlaying {
        return NowPlaying()
    }

    override fun getNPFromServer(): NowPlaying {
        TODO("Not yet implemented")
    }

    override fun getUpCFromLocaleStorage(): UpComing {
        return UpComing()
    }

    override fun getUpCFromServer(): UpComing {
        TODO("Not yet implemented")
    }

}