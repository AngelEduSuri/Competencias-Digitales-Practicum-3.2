package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories

import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.LocalStorageService
import javax.inject.Inject

private const val USER = "Usuario"

class LocalDataRepository @Inject constructor(private val localStorageService: LocalStorageService) {

    suspend fun saveUserName(name: String) = localStorageService.saveUserName(name)

    suspend fun getUserName() = localStorageService.getUserName().ifEmpty { USER }

    suspend fun saveShowInitialMessage(isShow: Boolean) =
        localStorageService.saveShowInitialMessage(isShow)

    suspend fun getShowInitialMessage() = localStorageService.getShowInitialMessage()

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

    suspend fun saveCloseDialogMessage(showDialog: Boolean) =
        localStorageService.saveIfMessageDialogShouldShowing(showDialog)

    suspend fun getCloseDialogMessage() = localStorageService.getIfMessageDialogShouldShowing()

    suspend fun saveAllTopicsIsCompleted(isComplete: Boolean) =
        localStorageService.saveIfAllTopicsIsCompleted(isComplete)

    suspend fun getAllTopicsIsCompleted() = localStorageService.getIfAllTopicIsCompleted()

    suspend fun saveUserResult(result: String) = localStorageService.saveUserResult(result)

    suspend fun getUserResult() = localStorageService.getUserResult()

    suspend fun saveTestCompleted(isComplete: Boolean) =
        localStorageService.saveIfTestIsCompleted(isComplete)

    suspend fun getTestCompleted() = localStorageService.getIfTestIsCompleted()
}