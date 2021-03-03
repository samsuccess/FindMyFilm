package ru.example.findmyfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.example.findmyfilm.model.NowPlaying
import ru.example.findmyfilm.model.Repository
import ru.example.findmyfilm.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
        private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) : ViewModel() {
    private val repositoryImpl: Repository = RepositoryImpl()
    fun getLiveData(): LiveData<AppState> = liveDataToObserve

    fun getNPFromLocaleSource() = getDataNPFromLocalSource()
//    fun getNPFromRemoteSource () = getDataNPFromRemoteSource()
//    fun getUpCFromLocaleSource() = getDataUpCFromLocalSource()
//    fun getUpCFromRemoteSource() = getDataUpCFromRemoteSource()

    private fun getDataNPFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            val dataNow = repositoryImpl.getNPFromLocaleStorage()
            val dataUpCom = repositoryImpl.getUpCFromLocaleStorage()
            liveDataToObserve.postValue(
                    AppState.Success(dataNow, dataUpCom))
        }.start()
    }
}