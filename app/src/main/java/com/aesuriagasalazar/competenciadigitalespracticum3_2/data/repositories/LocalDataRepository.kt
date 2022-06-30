package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories

import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.LocalStorageService
import javax.inject.Inject

class LocalDataRepository @Inject constructor(private val localStorageService: LocalStorageService) {

    suspend fun saveUserName(name: String) = localStorageService.saveUserName(name)

    suspend fun getUserName(): String {
        val name = localStorageService.getUserName()
        return name.ifEmpty { "Usuario" }
    }

    suspend fun saveShowInitialMessage(isShow: Boolean) {
        localStorageService.saveShowInitialMessage(isShow)
    }

    suspend fun getShowInitialMessage(): Boolean = localStorageService.getShowInitialMessage()


}