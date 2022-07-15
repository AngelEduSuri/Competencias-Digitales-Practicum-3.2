package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.module

import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.RemoteStorageService
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.impl.RemoteStorageServiceImpl
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    fun firestoreInstance() = Firebase.firestore

    @Provides
    fun provideRemoteData(firestore: FirebaseFirestore): RemoteStorageService {
        return RemoteStorageServiceImpl(firestore)
    }
}