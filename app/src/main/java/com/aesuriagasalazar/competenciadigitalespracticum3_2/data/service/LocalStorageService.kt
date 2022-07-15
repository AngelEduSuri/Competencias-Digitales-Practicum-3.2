package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service


interface LocalStorageService {
    suspend fun saveShowInitialMessage(show: Boolean)
    suspend fun getShowInitialMessage(): Boolean
    suspend fun saveUserName(name: String)
    suspend fun getUserName(): String
    suspend fun saveTopicFileComplete(isComplete: Boolean)
    suspend fun getTopicFileComplete(): Boolean
    suspend fun saveTopicTypeFileComplete(isComplete: Boolean)
    suspend fun getTopicTypeFileIsComplete(): Boolean
    suspend fun saveTopicDigitalToolsComplete(isComplete: Boolean)
    suspend fun getTopicDigitalToolsIsComplete(): Boolean
    suspend fun saveTopicShareFilesComplete(isComplete: Boolean)
    suspend fun getTopicShareFilesIsComplete(): Boolean
    suspend fun saveIfMessageDialogShouldShowing(isShow: Boolean)
    suspend fun getIfMessageDialogShouldShowing(): Boolean
    suspend fun saveIfAllTopicsIsCompleted(isComplete: Boolean)
    suspend fun getIfAllTopicIsCompleted(): Boolean
    suspend fun saveUserResult(result: String)
    suspend fun getUserResult(): String
    suspend fun saveIfTestIsCompleted(isComplete: Boolean)
    suspend fun getIfTestIsCompleted(): Boolean
}