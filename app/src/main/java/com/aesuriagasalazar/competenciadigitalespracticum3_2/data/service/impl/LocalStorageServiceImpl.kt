package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.impl

import android.content.Context
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.LocalStorageService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val PREF_NAME = "digitalSkills"
private const val INITIAL_MESSAGE = "initial"
private const val USER_NAME = "username"
private const val TOPIC_FILE = "topic_file"
private const val TOPIC_TYPE_FILE = "topic_type_file"
private const val TOPIC_DIGITAL_TOOLS = "topic_digital_tools"
private const val TOPIC_SHARE_FILES = "topic_share_files"
private const val DIALOG_MESSAGE_TOPICS = "dialog_message_topic"
private const val ALL_TOPICS_COMPLETED = "all_topics_completed"

class LocalStorageServiceImpl @Inject constructor(@ApplicationContext private val context: Context) :
    LocalStorageService {

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    // Extension function for save values with strings const

    override suspend fun saveShowInitialMessage(show: Boolean) {
        INITIAL_MESSAGE.saveBoolean(show)
    }

    override suspend fun getShowInitialMessage() = INITIAL_MESSAGE.getBoolean()

    override suspend fun saveUserName(name: String) {
        USER_NAME.saveString(name)
    }

    override suspend fun getUserName() = USER_NAME.getString() ?: ""

    override suspend fun saveTopicFileComplete(isComplete: Boolean) {
        TOPIC_FILE.saveBoolean(isComplete)
    }

    override suspend fun getTopicFileComplete() = TOPIC_FILE.getBoolean()

    override suspend fun saveTopicTypeFileComplete(isComplete: Boolean) {
        TOPIC_TYPE_FILE.saveBoolean(isComplete)
    }

    override suspend fun getTopicTypeFileIsComplete() = TOPIC_TYPE_FILE.getBoolean()

    override suspend fun saveTopicDigitalToolsComplete(isComplete: Boolean) {
        TOPIC_DIGITAL_TOOLS.saveBoolean(isComplete)
    }

    override suspend fun getTopicDigitalToolsIsComplete() = TOPIC_DIGITAL_TOOLS.getBoolean()

    override suspend fun saveTopicShareFilesComplete(isComplete: Boolean) {
        TOPIC_SHARE_FILES.saveBoolean(isComplete)
    }

    override suspend fun getTopicShareFilesIsComplete() = TOPIC_SHARE_FILES.getBoolean()

    override suspend fun saveIfMessageDialogShouldShowing(isShow: Boolean) {
        DIALOG_MESSAGE_TOPICS.saveBoolean(isShow)
    }

    override suspend fun getIfMessageDialogShouldShowing() = DIALOG_MESSAGE_TOPICS.getBoolean()

    override suspend fun saveIfAllTopicsIsCompleted(isComplete: Boolean) {
        ALL_TOPICS_COMPLETED.saveBoolean(isComplete)
    }

    override suspend fun getIfAllTopicIsCompleted() = ALL_TOPICS_COMPLETED.getBoolean()

    // Set and get values in shared preferences

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