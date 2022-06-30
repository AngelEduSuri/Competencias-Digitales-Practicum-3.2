package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.impl

import android.content.Context
import android.util.Log
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.LocalStorageService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val PREF_NAME = "digitalSkills"
private const val INITIAL_MESSAGE = "initial"
private const val USER_NAME = "username"

class LocalStorageServiceImpl @Inject constructor(@ApplicationContext private val context: Context): LocalStorageService {

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    override suspend fun saveShowInitialMessage(show: Boolean) {
        INITIAL_MESSAGE.saveBoolean(show)
    }

    override suspend fun getShowInitialMessage(): Boolean {
        return INITIAL_MESSAGE.getBoolean()
    }

    override suspend fun saveUserName(name: String) {
        USER_NAME.saveString(name)
    }

    override suspend fun getUserName(): String {
        return USER_NAME.getString()!!
    }

    private suspend fun String.saveBoolean(boolean: Boolean) = withContext(Dispatchers.IO) {
        editor.putBoolean(this@saveBoolean, boolean)
        editor.commit()
    }

    private suspend fun String.getBoolean() = withContext(Dispatchers.IO) {
        sharedPref.getBoolean(this@getBoolean, false)
    }

    private suspend fun String.saveString(string: String) = withContext(Dispatchers.IO) {
        editor.putString(this@saveString, string)
        editor.commit()
    }

    private suspend fun String.getString() = withContext(Dispatchers.IO) {
        sharedPref.getString(this@getString, "")
    }
}