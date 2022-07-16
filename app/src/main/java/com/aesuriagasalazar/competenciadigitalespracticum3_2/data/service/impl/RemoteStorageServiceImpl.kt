package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.impl

import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.RemoteStorageService
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TestScore
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteStorageServiceImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    RemoteStorageService {

    override suspend fun saveResultTest(uid: String, result: TestScore) = flow {
        firestore.collection("user-result")
            .document(uid)
            .collection("results")
            .add(result)
            .await()
        emit(true)
    }.catch { emit(false) }

    override suspend fun getResultTest(): Flow<List<TestScore>> {
        TODO("Get test scores from firestore, implement logic if is necessary")
    }
}