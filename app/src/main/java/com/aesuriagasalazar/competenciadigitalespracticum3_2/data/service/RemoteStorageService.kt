package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service

interface RemoteStorageService {

    suspend fun saveResultTest()
    suspend fun saveUserReview()
}