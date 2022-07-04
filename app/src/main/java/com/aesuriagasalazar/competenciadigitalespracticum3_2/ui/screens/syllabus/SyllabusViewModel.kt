package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.syllabus

import androidx.lifecycle.ViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.sources.StaticDataSource
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.Syllabus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SyllabusViewModel @Inject constructor(
    syllabusData: StaticDataSource
) : ViewModel() {

    private val _syllabusUiState =
        MutableStateFlow(SyllabusUiState(listSyllabus = syllabusData.syllabusList))
    val syllabusUiState: StateFlow<SyllabusUiState> = _syllabusUiState.asStateFlow()

}

data class SyllabusUiState(
    val listSyllabus: List<Syllabus> = emptyList()
)