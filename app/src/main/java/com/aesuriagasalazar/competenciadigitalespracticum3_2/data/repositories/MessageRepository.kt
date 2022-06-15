package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories

import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.datasources.LocalDataSource
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.datasources.SharedPrefDataSource
import javax.inject.Inject

class MessageRepository @Inject constructor(private val localDataSource: LocalDataSource, private val pref: SharedPrefDataSource) {

    fun getInitialMessage() = localDataSource.initialMessage

    fun saveNotShowInitialMessage(showing: Boolean) = pref.saveBooleanPref(showing)
}