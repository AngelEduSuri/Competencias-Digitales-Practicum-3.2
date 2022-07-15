package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.module

import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.LocalStorageService
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.impl.LocalStorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindLocalStorageService(impl: LocalStorageServiceImpl): LocalStorageService


}
