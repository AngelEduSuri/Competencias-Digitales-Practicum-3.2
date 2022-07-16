package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service

import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TestScore
import kotlinx.coroutines.flow.Flow

interface RemoteStorageService {
    suspend fun saveResultTest(uid: String, result: TestScore): Flow<Boolean>
    suspend fun getResultTest(): Flow<List<TestScore>>
}