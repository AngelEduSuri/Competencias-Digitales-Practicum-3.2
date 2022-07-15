package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service

import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TestResult
import kotlinx.coroutines.flow.Flow

interface RemoteStorageService {

    suspend fun saveResultTest(uid: String, result: TestResult): Flow<Boolean>
    suspend fun getResultTest(): Flow<List<TestResult>>
}