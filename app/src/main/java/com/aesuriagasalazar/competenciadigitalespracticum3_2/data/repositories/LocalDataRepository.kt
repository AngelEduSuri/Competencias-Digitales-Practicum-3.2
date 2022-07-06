package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories

import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.LocalStorageService
import javax.inject.Inject

class LocalDataRepository @Inject constructor(private val localStorageService: LocalStorageService) {

    suspend fun saveUserName(name: String) = localStorageService.saveUserName(name)

    suspend fun getUserName(): String {
        val name = localStorageService.getUserName()
        return name.ifEmpty { "Usuario" }
    }

    suspend fun saveShowInitialMessage(isShow: Boolean) =
        localStorageService.saveShowInitialMessage(isShow)

    suspend fun getShowInitialMessage(): Boolean = localStorageService.getShowInitialMessage()

    suspend fun saveTopicFileComplete(isComplete: Boolean) =
        localStorageService.saveTopicFileComplete(isComplete)

    suspend fun getTopicFileIsComplete() = localStorageService.getTopicFileComplete()

    suspend fun saveTopicTypeFileComplete(isComplete: Boolean) =
        localStorageService.saveTopicTypeFileComplete(isComplete)

    suspend fun getTopicTypeFileIsComplete() = localStorageService.getTopicTypeFileIsComplete()

    suspend fun saveTopicDigitalToolsComplete(isComplete: Boolean) =
        localStorageService.saveTopicDigitalToolsComplete(isComplete)

    suspend fun getTopicDigitalToolsIsComplete() =
        localStorageService.getTopicDigitalToolsIsComplete()

    suspend fun saveTopicShareFilesComplete(isComplete: Boolean) =
        localStorageService.saveTopicShareFilesComplete(isComplete)

    suspend fun getTopicShareFilesIsComplete() = localStorageService.getTopicShareFilesIsComplete()
}