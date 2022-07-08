package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.lessonfinished

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories.LocalDataRepository
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TopicSyllabusId
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation.RoutesApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonFinishedViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val localDataRepository: LocalDataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LessonFinishedUiState())
    val uiState: StateFlow<LessonFinishedUiState> = _uiState.asStateFlow()

    init {
        checkCountLesson()
    }

    private fun checkCountLesson() = viewModelScope.launch {
        val idLesson = savedStateHandle.get<TopicSyllabusId>(RoutesApp.LessonFinished.arg)
        _uiState.update {
            val countLesson = when (idLesson) {
                TopicSyllabusId.NONE -> "0/4"
                TopicSyllabusId.TOPIC_FILE -> {
                    localDataRepository.saveTopicFileComplete(true)
                    "1/4"
                }
                TopicSyllabusId.TYPE_OF_FILE -> {
                    localDataRepository.saveTopicTypeFileComplete(true)
                    "2/4"
                }
                TopicSyllabusId.DIGITAL_TOOLS -> {
                    localDataRepository.saveTopicDigitalToolsComplete(true)
                    "3/4"
                }
                TopicSyllabusId.SHARE_FILE -> {
                    localDataRepository.saveTopicShareFilesComplete(true)
                    "4/4"
                }
                else -> "0/4"
            }
            it.copy(countLesson = countLesson, buttonVisible = true)
        }
    }
}

data class LessonFinishedUiState(
    val countLesson: String = "",
    val buttonVisible: Boolean = false
)