package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.syllabus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories.LocalDataRepository
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.sources.StaticDataSource
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.Syllabus
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TopicSyllabusId.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SyllabusViewModel @Inject constructor(
    private val syllabusData: StaticDataSource,
    private val localRepository: LocalDataRepository
) : ViewModel() {

    private val _syllabusUiState =
        MutableStateFlow(SyllabusUiState())
    val syllabusUiState: StateFlow<SyllabusUiState> = _syllabusUiState.asStateFlow()

    fun checkIfTopicIsComplete() = viewModelScope.launch {
        val newListState = syllabusData.getSyllabusList().map {
            when (it.id) {
                TOPIC_FILE -> {
                    it.copy(isComplete = localRepository.getTopicFileIsComplete())
                }
                TYPE_OF_FILE -> {
                    it.copy(isComplete = localRepository.getTopicTypeFileIsComplete())
                }
                DIGITAL_TOOLS -> {
                    it.copy(isComplete = localRepository.getTopicDigitalToolsIsComplete())
                }
                SHARE_FILE -> {
                    it.copy(isComplete = localRepository.getTopicShareFilesIsComplete())
                }
                NONE -> {
                    it.copy(isComplete = false)
                }
            }
        }

        _syllabusUiState.update { it.copy(listSyllabus = newListState) }

        checkIfAllTopicIsCompleted()
    }

    private fun checkIfAllTopicIsCompleted() = viewModelScope.launch {
        _syllabusUiState.update {
            it.copy(
                showDialogMessage = it.listSyllabus.all { syllabus ->
                    syllabus.isComplete && !localRepository.getCloseDialogMessage()
                },
            )
        }
    }

    fun onCloseDialogMessage() = viewModelScope.launch {
        localRepository.saveCloseDialogMessage(true)
        localRepository.saveAllTopicsIsCompleted(true)
        checkIfAllTopicIsCompleted()
    }
}

data class SyllabusUiState(
    val listSyllabus: List<Syllabus> = emptyList(),
    val showDialogMessage: Boolean = false,
)