package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service


interface LocalStorageService {
    suspend fun saveShowInitialMessage(show: Boolean)
    suspend fun getShowInitialMessage(): Boolean
    suspend fun saveUserName(name: String)
    suspend fun getUserName(): String
}