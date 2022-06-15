package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.datasources

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val PREF_NAME = "digitalSkills"
private const val INITIAL_MESSAGE = "initial"

class SharedPrefDataSource @Inject constructor(@ApplicationContext private val context: Context) {

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    fun saveBooleanPref(value: Boolean) {
        INITIAL_MESSAGE.saveBoolean(value)
    }

    fun getBooleanPref() = INITIAL_MESSAGE.getBoolean()

    private fun String.saveBoolean(boolean: Boolean) {
        editor.putBoolean(this, boolean)
        editor.commit()
    }

    private fun String.getBoolean() = sharedPref.getBoolean(this, false)
}