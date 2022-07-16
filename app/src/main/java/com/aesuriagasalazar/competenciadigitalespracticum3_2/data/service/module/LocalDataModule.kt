package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.module

import android.content.Context
import android.content.SharedPreferences
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.LocalStorageService
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.impl.LocalStorageServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    private val prefName = "digital_skills"

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    @Provides
    fun provideSharedEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor =
        sharedPreferences.edit()

    @Provides
    fun provideLocalStorageService(
        sharedPreferences: SharedPreferences,
        editor: SharedPreferences.Editor
    ): LocalStorageService {
        return LocalStorageServiceImpl(sharedPreferences, editor)
    }
}
